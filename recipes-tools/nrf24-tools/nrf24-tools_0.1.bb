SUMMARY  = "Tools for pcDuino boards"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRCREV = "${AUTOREV}"

PR = "r0"
PV = "1.1+git${SRCPV}"

SRC_URI = "git://github.com/geomatsi/nrf24-tools.git;protocol=https \
        file://nrf24-mosquitto-pub.service \
        "

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit cmake systemd

SYSTEMD_SERVICE_${PN} = "nrf24-mosquitto-pub.service"

DEPENDS += "libnrf24 protobuf-c protobuf-c-native mosquitto json-c"

EXTRA_OECMAKE_append = " \
	-DKERNEL_DIR:PATH=${STAGING_KERNEL_DIR} \
	-DNRF24_CONN='SBC'"

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

do_install_append_orange-pi-zero () {
	install -d ${D}${sysconfdir}/nrf24
	install -m 0755 ${S}/conf/orange-pi-zero.cfg ${D}${sysconfdir}/nrf24/default.cfg
}

do_install_append_orange-pi-zero-plus2 () {
	install -d ${D}${sysconfdir}/nrf24
	install -m 0755 ${S}/conf/orange-pi-zero-plus2.cfg ${D}${sysconfdir}/nrf24/default.cfg
}

do_install_append_orange-pi-one () {
	install -d ${D}${sysconfdir}/nrf24
	install -m 0755 ${S}/conf/orange-pi-one.cfg ${D}${sysconfdir}/nrf24/default.cfg
}

do_install_append_pcduino () {
	install -d ${D}${sysconfdir}/nrf24
	install -m 0755 ${S}/conf/pcduino-upstream.cfg ${D}${sysconfdir}/nrf24/default.cfg
}

do_install_append_pcduino-lite-wifi () {
	install -d ${D}${sysconfdir}/nrf24
	install -m 0755 ${S}/conf/pcduino-legacy.cfg ${D}${sysconfdir}/nrf24/default.cfg
}
