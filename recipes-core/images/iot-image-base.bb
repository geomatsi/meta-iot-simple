SUMMARY = "A small IoT image with basic tools and libs"

LICENSE = "MIT"

IMAGE_INSTALL = "packagegroup-iot"
IMAGE_FEATURES += "tools-debug ssh-server-dropbear"

inherit core-image
