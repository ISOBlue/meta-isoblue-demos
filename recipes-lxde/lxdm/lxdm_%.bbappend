FILESEXTRAPATHS_prepend := "${THISDIR}/lxdm:"

SRC_URI += " \
    file://logout-fixes.patch \
    file://root-autologin.patch \
    file://0001-lxdm.service-kill-lxsession-explicitely-with-sigkill.patch \
"
