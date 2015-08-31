SUMMARY  = "Tools for pcDuino boards"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRCREV = "${AUTOREV}"

PR = "r0"
PV = "1.1+git${SRCPV}"

SRC_URI = "git://github.com/geomatsi/pcduino-tools.git;protocol=git"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit cmake

DEPENDS += "libnrf24 protobuf-c protobuf-c-native mosquitto"

EXTRA_OECMAKE = "-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} -DWITH_NRF24_MOSQUITTO_TEST=ON -DNRF24_CONN='PCDUINO'"

do_install () {
    install -d ${D}/${sbindir}
	install -m 0755 ${B}/nrf24_test ${D}/${sbindir}/nrf24_test
	install -m 0755 ${B}/nrf24_mosquitto_test ${D}/${sbindir}/nrf24_mosquitto_test
}
