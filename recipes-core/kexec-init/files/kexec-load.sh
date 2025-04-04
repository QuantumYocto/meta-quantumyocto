#!/bin/sh

if [ -f /boot/bzImage ]; then
    echo "Loading bzImage into kexec..."
    /usr/sbin/kexec -l /boot/bzImage --reuse-cmdline
fi
