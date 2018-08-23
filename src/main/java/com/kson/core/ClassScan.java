package com.kson.core;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClassScan {

    private ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    private Set<Class> classSet = new LinkedHashSet<>();
    private String packageName;

    public Set<Class> getClassSet() {
        return classSet;
    }

    public void setClassSet(Set<Class> classSet) {
        this.classSet = classSet;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ClassScan(String packageName) {
        this.packageName = packageName;
    }

    public void scanAllClass() throws Exception {
        Enumeration<URL> resources = classLoader.getResources(getResourceName(packageName));

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if ("file".equals(url.getProtocol())) {
                scanClassByFile(url.getPath(), packageName);
            }
        }
    }

    private void scanClassByFile(String filePath, String packageName) {
        File file = new File(filePath);
        if (!file.isDirectory() || !file.exists()) {
            return;
        }
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                scanClassByFile(f.getPath(), packageName + "." + f.getName());
            } else {
                if (f.getName().endsWith(".class")) {
                    try {
                        classSet.add(classLoader.loadClass(getClassName(packageName, f.getName())));
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private String getResourceName(String packageName) {
        return packageName.replace(".", "/");
    }

    private String getClassName(String packageName, String fileName) {
        String className = packageName + "." + fileName;
        // 去掉最后的.class
        return className.substring(0, className.length() - 6);
    }

}
