/*
 * Copyright (C) 2021 The LineageOS Project
 *
 * SPDX-License-Identifier: Apache-2.0
 */

#include <android-base/logging.h>
#include <android-base/properties.h>
#include <libinit_utils.h>

#include <libinit_variant.h>

using android::base::GetProperty;

#define HWC_PROP "ro.boot.hardware.sku"

void search_variant(const std::vector<variant_info_t> variants) {
    std::string hwc_value = GetProperty(HWC_PROP, "");

    for (const auto& variant : variants) {
        if (variant.hwc_value == "" || variant.hwc_value == hwc_value) {
            set_variant_props(variant);
            break;
        }
    }
}

void set_variant_props(const variant_info_t variant) {
    set_ro_build_prop("model", variant.model, true);
    property_override("vendor.usb.product_string", variant.model, true);

    if (access("/system/bin/recovery", F_OK) != 0) {
        property_override("bluetooth.device.default_name", variant.model, true);
    }
}
