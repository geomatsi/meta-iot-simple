SUMMARY  = "Tools for pcDuino boards"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRCREV = "${AUTOREV}"

PR = "r0"
PV = "1.1+git${SRCPV}"

SRC_URI = "git://github.com/geomatsi/nrf24-tools.git;protocol=https \
        file://serial_sensor \
        file://nrf24-mosquitto-pub.service \
        "

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit cmake update-rc.d systemd

SYSTEMD_SERVICE_${PN} = "nrf24-mosquitto-pub.service"

DEPENDS += "libnrf24 protobuf-c protobuf-c-native mosquitto"

EXTRA_OECMAKE_append_pcduino = " \
	-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} \
	-DWITH_NRF24_MOSQUITTO_PUB=ON \
	-DWITH_SERIAL_MOSQUITTO_TEST=ON \
	-DNRF24_CONN='PCDUINO_UPSTREAM'"

EXTRA_OECMAKE_append_pcduino-lite-wifi = " \
	-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} \
	-DWITH_NRF24_MOSQUITTO_PUB=ON \
	-DWITH_SERIAL_MOSQUITTO_TEST=ON \
	-DNRF24_CONN='PCDUINO_LEGACY'"

EXTRA_OECMAKE_append_beaglebone = " \
	-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} \
	-DWITH_NRF24_MOSQUITTO_PUB=ON \
	-DNRF24_CONN='BONE'"

EXTRA_OECMAKE_append_orange-pi-one = " \
	-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} \
	-DWITH_NRF24_MOSQUITTO_PUB=ON \
	-DNRF24_CONN='ORANGE_PI_ONE'"

EXTRA_OECMAKE_append_orange-pi-zero = " \
	-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} \
	-DWITH_NRF24_MOSQUITTO_PUB=ON \
	-DNRF24_CONN='ORANGE_PI_ZERO'"

INITSCRIPT_NAME = "serial_sensor"
INITSCRIPT_PARAMS = "defaults 75"

do_install () {
	install -d ${D}/${sbindir}
	install -m 0755 ${B}/nrf24_test_ptx ${D}/${sbindir}/nrf24_test_ptx
	install -m 0755 ${B}/nrf24_test_prx ${D}/${sbindir}/nrf24_test_prx
	install -m 0755 ${B}/nrf24_test_hub ${D}/${sbindir}/nrf24_test_hub
	install -m 0755 ${B}/nrf24_dump_regs ${D}/${sbindir}/nrf24_dump_regs
	install -m 0755 ${B}/nrf24_mosquitto_pub ${D}/${sbindir}/nrf24_mosquitto_pub

	install -d ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/nrf24-mosquitto-pub.service ${D}${systemd_unitdir}/system/
}

do_install_append_pcduino () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/serial_sensor ${D}${sysconfdir}/init.d/serial_sensor
	install -m 0755 ${B}/serial_mosquitto_test ${D}/${sbindir}/serial_mosquitto_test
}

do_install_append_pcduino-lite-wifi () {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/serial_sensor ${D}${sysconfdir}/init.d/serial_sensor
	install -m 0755 ${B}/serial_mosquitto_test ${D}/${sbindir}/serial_mosquitto_test
}
