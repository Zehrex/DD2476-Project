23
https://raw.githubusercontent.com/WeBankFinTech/Exchangis/master/modules/executor/engine/datax/datax-core/src/main/java/com/alibaba/datax/core/util/container/JarLoader.java
package com.alibaba.datax.core.util.container;

import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.core.util.FrameworkErrorCode;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供Jar隔离的加载机制，会把传入的路径、及其子路径、以及路径中的jar文件加入到class path。
 */
public class JarLoader extends URLClassLoader {
    public JarLoader(String[] paths) {
        this(paths, JarLoader.class.getClassLoader());
    }

    public JarLoader(String[] paths, ClassLoader parent) {
        super(getURLs(paths), parent);
    }

    private static URL[] getURLs(String[] paths) {
        Validate.isTrue(null != paths && 0 != paths.length,
                "jar包路径不能为空.");

        List<String> dirs = new ArrayList<String>();
        for (String path : paths) {
            dirs.add(path);
            JarLoader.collectDirs(path, dirs);
        }

        List<URL> urls = new ArrayList<URL>();
        for (String path : dirs) {
            urls.addAll(doGetURLs(path));
        }
        return urls.toArray(new URL[0]);
    }

    private static void collectDirs(String path, List<String> collector) {
        if (null == path || StringUtils.isBlank(path)) {
            return;
        }

        File current = new File(path);
        if (!current.exists() || !current.isDirectory()) {
            return;
        }

        if(null != current.listFiles()) {
            for (File child : current.listFiles()) {
                if (!child.isDirectory()) {
                    continue;
                }

                collector.add(child.getAbsolutePath());
                collectDirs(child.getAbsolutePath(), collector);
            }
        }
    }

    private static List<URL> doGetURLs(final String path) {
        Validate.isTrue(!StringUtils.isBlank(path), "jar包路径不能为空.");

        File jarPath = new File(path);

        Validate.isTrue(jarPath.exists() && jarPath.isDirectory(),
                "jar包路径必须存在且为目录.");

		/* set filter */
        FileFilter jarFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".jar");
            }
        };

		/* iterate all jar */
        File[] allJars = new File(path).listFiles(jarFilter);
        List<URL> jarURLs = new ArrayList<URL>(allJars.length);

        for (int i = 0; i < allJars.length; i++) {
            try {
                jarURLs.add(allJars[i].toURI().toURL());
            } catch (Exception e) {
                throw DataXException.asDataXException(
                        FrameworkErrorCode.PLUGIN_INIT_ERROR,
                        "系统加载jar包出错", e);
            }
        }

        return jarURLs;
    }

    /**
     * change the order to load class
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)){
            //First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if(c == null){
                long t0 = System.nanoTime();
                try {
                    //invoke findClass in this class
                    c = findClass(name);
                }catch(ClassNotFoundException e){
                    // ClassNotFoundException thrown if class not found
                }
                if(c == null){
                    return super.loadClass(name, resolve);
                }
                sun.misc.PerfCounter.getFindClasses().addElapsedTimeFrom(t0);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
            if(resolve){
                resolveClass(c);
            }
            return c;
        }
    }

    /**
     * defined class by bytes
     * @param name
     * @param bytes
     * @return
     */
    public Class<?> loadClass(String name, byte[] bytes){
        return this.defineClass(name, bytes, 0, bytes.length);
    }
}
