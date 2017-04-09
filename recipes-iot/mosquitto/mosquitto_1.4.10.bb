SUMMARY = "Open source MQTT v3.1 implemention"
DESCRIPTION = "Mosquitto is an open source (BSD licensed) message broker that implements the MQ Telemetry Transport protocol version 3.1. MQTT provides a lightweight method of carrying out messaging using a publish/subscribe model. "
HOMEPAGE = "http://mosquitto.org/"
SECTION = "console/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=62ddc846179e908dc0c8efec4a42ef20"

PR = "r0"

SRC_URI = "http://mosquitto.org/files/source/mosquitto-${PV}.tar.gz \
           file://build.patch \
           file://mosquitto.service \
"

SRC_URI[md5sum] = "61839b47b58c5799aab76584f13ed66f"
SRC_URI[sha256sum] = "437648d68a4a781dd8d913814cd5451f59ab4a5fcf84cccaf7c36e6a07459770"

inherit cmake pkgconfig useradd systemd

DEPENDS = "openssl util-linux c-ares"

RDEPENDS_${PN} += "libcrypto libssl"

SYSTEMD_SERVICE_${PN} = "mosquitto.service"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " --system --no-create-home  --shell /bin/false --user-group mosquitto"

export LIB_SUFFIX="${@d.getVar('baselib', True).replace('lib', '')}"

EXTRA_OECMAKE += " -DCMAKE_SKIP_RPATH=ON "

do_install_append() {
    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/mosquitto.service ${D}${systemd_unitdir}/system/
}

PACKAGES += "libmosquitto1 libmosquittopp1 ${PN}-clients"

FILES_${PN} = "${sbindir}/mosquitto \
               ${bindir}/mosquitto_passwd \
               ${sysconfdir}/mosquitto \
               ${systemd_unitdir}/system/mosquitto.service \
"

FILES_libmosquitto1 = "${libdir}/libmosquitto.so*"

FILES_libmosquittopp1 = "${libdir}/libmosquittopp.so*"

FILES_${PN}-clients = "${bindir}/mosquitto_pub \
                       ${bindir}/mosquitto_sub \
"
