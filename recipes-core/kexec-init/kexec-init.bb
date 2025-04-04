SUMMARY = "Service to load bzImage into kexec on boot/reboot"
LICENSE = "CLOSED"

SRC_URI = "file://kexec-load.sh \
           file://kexec-load.service \
           file://override.conf"

do_install() {
    install -d ${D}/usr/local/bin
    install -m 0755 ${WORKDIR}/sources-unpack/kexec-load.sh ${D}/usr/local/bin/kexec-load.sh

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/sources-unpack/kexec-load.service ${D}${systemd_system_unitdir}/kexec-load.service

    install -d ${D}${systemd_system_unitdir}/reboot.target.d
    install -m 0644 ${WORKDIR}/sources-unpack/override.conf ${D}${systemd_system_unitdir}/reboot.target.d/override.conf
}

FILES:${PN} += "/usr/local/bin/kexec-load.sh"
FILES:${PN} += "${systemd_system_unitdir}/kexec-load.service"
FILES:${PN} += "${systemd_system_unitdir}/reboot.target.d/override.conf"

SYSTEMD_SERVICE:${PN} = "kexec-load.service"
inherit systemd
