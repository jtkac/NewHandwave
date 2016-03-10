LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)


	
LOCAL_MODULE    := touch_free_library
LOCAL_SRC_FILES := motion_averager.cpp
LOCAL_LDLIBS := -libtouch_free_library
LOCAL_LDLIBS +=  -llog -ldl


include $(BUILD_SHARED_LIBRARY)