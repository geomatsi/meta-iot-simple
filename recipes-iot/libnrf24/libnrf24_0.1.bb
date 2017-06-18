SUMMARY = "nRF24L01 library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

SRCREV = "${AUTOREV}"

PR = "r0"
PV = "1.1+git${SRCPV}"

SRC_URI = "git://github.com/geomatsi/libnrf24.git;protocol=https"

S = "${WORKDIR}/git"

PARALLEL_MAKE = ""

do_compile() {
	make CROSS_COMPILE="${TARGET_SYS}-" TARGET="${TARGET_OS}" CC="${CC}"
}

do_install () {
	install -d ${D}/${libdir}
	install -m 0644 ${S}/libnrf24_${TARGET_OS}.a ${D}/${libdir}/libnrf24.a

	install -d ${D}${includedir}/linux/nrf24
	install -m 0644 ${S}/include/RF24.h ${D}${includedir}/linux/nrf24/RF24.h
	install -m 0644 ${S}/include/nRF24L01.h ${D}${includedir}/linux/nrf24/nRF24L01.h
}

