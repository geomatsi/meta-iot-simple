SUMMARY  = "Tools for pcDuino boards"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRCREV = "${AUTOREV}"

PR = "r0"
PV = "1.1+git${SRCPV}"

SRC_URI = "git://github.com/geomatsi/nrf24-tools.git;protocol=git \
        file://serial_sensor \
        "

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit cmake update-rc.d

DEPENDS += "libnrf24 protobuf-c protobuf-c-native mosquitto"

EXTRA_OECMAKE_append_pcduino = "-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} -DWITH_NRF24_MOSQUITTO_TEST=ON -DWITH_SERIAL_MOSQUITTO_TEST=ON -DNRF24_CONN='PCDUINO_UPSTREAM'"
EXTRA_OECMAKE_append_pcduino-lite-wifi = "-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} -DWITH_NRF24_MOSQUITTO_TEST=ON -DWITH_SERIAL_MOSQUITTO_TEST=ON -DNRF24_CONN='PCDUINO_LEGACY'"
EXTRA_OECMAKE_append_beaglebone = "-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} -DWITH_NRF24_MOSQUITTO_TEST=ON -DNRF24_CONN='BONE'"
EXTRA_OECMAKE_append_orange-pi-one = "-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} -DWITH_NRF24_MOSQUITTO_TEST=ON -DWITH_SERIAL_MOSQUITTO_TEST=ON -DNRF24_CONN='PCDUINO_UPSTREAM'"

INITSCRIPT_NAME = "serial_sensor"
INITSCRIPT_PARAMS = "defaults 75"

do_install () {
    install -d ${D}/${sbindir}
	install -m 0755 ${B}/nrf24_test ${D}/${sbindir}/nrf24_test
	install -m 0755 ${B}/nrf24_mosquitto_test ${D}/${sbindir}/nrf24_mosquitto_test
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
