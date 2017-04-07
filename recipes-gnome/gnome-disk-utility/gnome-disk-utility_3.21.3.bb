SUMMARY = "GNOME disk utility"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS = "avahi-ui glib-2.0 gnome-doc-utils gtk+3 intltool-native \
           libatasmart libcanberra libdvdread libgnome-keyring \
           libnotify libpwquality libsecret libunique libxslt-native \
           pkgconfig-native udisks2 virtual/libx11 xmlto-native xz"
RDEPENDS_${PN} = "adwaita-icon-theme-symbolic"

PR = "r4"

inherit gnomebase gtk-icon-cache
SRC_URI[archive.md5sum] = "265ed1aede9ef0570228ca6908e9df59"
SRC_URI[archive.sha256sum] = "f39508226268f39dafa8977ebcec1d36e694f8aa7c4a404d1ac50f76e8e57739"
GNOME_COMPRESS_TYPE="xz"

#SRC_URI += "\
#    file://disable-scrollkeeper.patch \
#    file://fix-dbus-interfaces.patch \
#    file://sysrooted-pkg-config.patch \
#    file://0001-Add-support-for-DeviceAutomountHint.patch \
#    file://0002-Require-libnotify-0.6.1.patch \
#"

EXTRA_OECONF += "--disable-gsd-plugin"

do_configure_prepend() {
    sed -i -e "s: help : :g" ${S}/Makefile.am
}

FILES_${PN} += "${datadir}/appdata ${datadir}/dbus-1/services"
FILES_${PN}-libs += "${libdir}/libgdu*.so.*"
