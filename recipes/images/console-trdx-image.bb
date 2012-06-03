#Angstrom image
DESCRIPTION = "Image booting to a console"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

#create the file /etc/timestamp
IMAGE_PREPROCESS_COMMAND = "rootfs_update_timestamp"

#IMAGE_LINGUAS = ""
#IMAGE_LINGUAS = "en-us"
#IMAGE_LINGUAS = "de-de fr-fr en-gb en-us pt-br es-es kn-in ml-in ta-in"
#ROOTFS_POSTPROCESS_COMMAND += 'install_linguas; '

ZZAPSPLASH = ' ${@base_contains("MACHINE_FEATURES", "screen", "psplash-zap", "",d)}'

DISTRO_UPDATE_ALTERNATIVES ??= ""
ROOTFS_PKGMANAGE_PKGS ?= '${@base_conditional("ONLINE_PACKAGE_MANAGEMENT", "none", "", "${ROOTFS_PKGMANAGE} ${DISTRO_UPDATE_ALTERNATIVES}", d)}'

CONMANPKGS ?= "connman connman-plugin-loopback connman-plugin-ethernet connman-plugin-wifi connman-systemd"
CONMANPKGS_libc-uclibc = ""


IMAGE_INSTALL += " \
	angstrom-task-boot \
	task-basic \
	${CONMANPKGS} \
	${ROOTFS_PKGMANAGE_PKGS} \
	task-base-extended \
	${SPLASH} \
	${ZZAPSPLASH} \
"

include trdx-extra.inc

IMAGE_DEV_MANAGER   = "udev"
IMAGE_INIT_MANAGER  = "sysvinit sysvinit-pidof"
IMAGE_INITSCRIPTS   = "initscripts"
IMAGE_LOGIN_MANAGER = "tinylogin shadow"

export IMAGE_BASENAME = "console-trdx-image"

inherit image
