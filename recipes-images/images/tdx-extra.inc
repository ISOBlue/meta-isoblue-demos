#additional packages to include in our Toradex images

# Copy Licenses to image /usr/share/common-licenses, on vybrid for flash size reasons only the manifest
COPY_LIC_MANIFEST ?= "1"
#Bug with several MACHINES build, license files for all arch packages stored wrongly
COPY_LIC_DIRS ?= "0"
COPY_LIC_DIRS_colibri-vf = "0"

add_rootfs_version () {
    echo -n "${IMAGE_NAME} ${PV}" >> ${IMAGE_ROOTFS}/etc/issue
    echo " ${TDX_VERDATE}" | sed 's/-//' >>${IMAGE_ROOTFS}/etc/issue
    echo "" >> ${IMAGE_ROOTFS}/etc/issue
}

# add the rootfs version to the welcome banner
ROOTFS_POSTPROCESS_COMMAND += " add_rootfs_version;"

#note that the kernel-modules meta package gets installed from
#meta-fsl-arm/conf/machine/include/imx-base.inc
IMAGE_INSTALL-MX6QDL = " \
    bmode-usb \
    libusbgx \
    libusbgx-examples \
    linuxptp \
    gpio-export \
    file \
    aspell \
    ptpd \
    fs-init \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    e2fsprogs-tune2fs \
    mimetypes \
"
IMAGE_INSTALL_append_mx6q = "${IMAGE_INSTALL-MX6QDL}"
IMAGE_INSTALL_append_mx6dl = "${IMAGE_INSTALL-MX6QDL}"

IMAGE_INSTALL_append_apalis-imx6 = " \
    u-boot-toradex-fsl-fw-utils \
"

IMAGE_INSTALL_append_mx6ull = " \
    bmode-usb \
    libusbgx \
    libusbgx-examples \
    linuxptp \
    ptpd \
    mtd-utils-ubifs \
"

#note that the kernel-modules meta package gets installed from
#meta-fsl-arm/conf/machine/include/imx-base.inc
IMAGE_INSTALL_append_apalis-imx6 = " \
    pciutils \
"

IMAGE_INSTALL += " \
    packagegroup-dotnet-deps \
    \
    crda \
    \
    alsa-utils \
    alsa-utils-aplay \
    alsa-utils-amixer \
    \
    sqlite3 \
    \
    curl \
    canutils \
    nfs-utils-client \
    openssh-scp \
    openssh-sftp \
    \
    bzip2 \
    gdbserver \
    grep \
    joe \
    minicom \
    ldd \
    lsof \
    mtd-utils \
    dosfstools \
    util-linux-fstrim \
    \
    devmem2 \
    evtest \
    hdparm \
    iperf3 \
    i2c-tools \
    memtester \
    mmc-utils-cos \
    rt-tests \
    stress \
    lmbench \
    nbench-byte \
    tinymembench \
    ethtool \
    \
    tdx-oak-sensors \
"

#Backports

BACKPORTS_WIFI_EXTRA = " \
    linux-firmware-ath10k    \
    linux-firmware-sd8686    \
    linux-firmware-sd8688    \
    linux-firmware-sd8787    \
    linux-firmware-sd8797    \
    linux-firmware-sd8887    \
    linux-firmware-ralink    \
    linux-firmware-rtl8192cu \
    linux-firmware-rtl8188eu \
    hostapd \
    linux-firmware-ath9k     \
    linux-firmware-iwlwifi   \
"

IMAGE_INSTALL_append_apalis-imx6 = " \
    ${BACKPORTS_WIFI_EXTRA} \
"
