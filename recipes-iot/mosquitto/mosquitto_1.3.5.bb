SUMMARY  = "MQTT broker"
DESCRIPTION = "Mosquitto is an open source MQTT broker"
HOMEPAGE = "http://www.mosquitto.org"

LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=89aa5ea5f32e4260d84c5d185ee3add4"

PV = "1.3.5"

SRC_URI = "http://mosquitto.org/files/source/mosquitto-1.3.5.tar.gz \
        file://mosquitto \
        "

inherit cmake pkgconfig useradd update-rc.d

DEPENDS += "c-ares"

SRC_URI[md5sum] = "55094ad4dc7c7985377f43d4fc3d09da"
SRC_URI[sha256sum] = "16eb3dbef183827665feee9288362c7352cd016ba04ca0402a0ccf857d1c2ab2"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = " --system --no-create-home  --shell /bin/false --user-group mosquitto"

INITSCRIPT_NAME = "mosquitto"
INITSCRIPT_PARAMS = "defaults 70"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/mosquitto ${D}${sysconfdir}/init.d/mosquitto
}
