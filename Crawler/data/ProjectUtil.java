4
https://raw.githubusercontent.com/justneon33/Sketchcode/master/app/src/main/java/com/sketch/code/two/util/ProjectUtil.java
package com.sketch.code.two.util;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.sketch.code.two.item.BackupedProject;
import com.sketch.code.two.item.SketchwareProject;
import com.sketch.code.two.item.SketchwareProjectConfiguration;
import com.sketch.code.two.item.SketchwareProjectResources;
import com.sketch.code.two.manager.CopyManager;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ProjectUtil {

    public static String SKETCHWARE_BASE_PATH = FileUtil.getExternalStorageDir().concat("/.sketchware");
    public static String SKETCHWARE_PROJECTS_PATH = SKETCHWARE_BASE_PATH.concat("/mysc/list");
    public static String SKETCHWARE_MYSC_PATH = SKETCHWARE_BASE_PATH.concat("/mysc");
    public static String SKETCHWARE_RESOURCES_PATH = SKETCHWARE_BASE_PATH.concat("/resources");

    public static String SKETCHCODE_BACKUP_BASE_PATH = FileUtil.getExternalStorageDir().concat("/sketchcode/backups");

    public ProjectUtil() {
        // making path for backups
        FileUtil.makeDir(SKETCHCODE_BACKUP_BASE_PATH);
    }


    public ArrayList<SketchwareProject> getProjects () {
        // init list with SketchwareProject object
        ArrayList<SketchwareProject> projects = new ArrayList<>();
        // init list for list files
        ArrayList<String> files = new ArrayList<>();
        // list files
        FileUtil.listDir(SKETCHWARE_PROJECTS_PATH, files);
        // adding projects to list
        for(String file : files) {
            // decrypting list file and set as HashMap
            HashMap<String, Object> project = JsonUtil.from(projectDecryptor.decryptListFile(file.concat("/project")));
            SketchwareProject sketchwareProject = new SketchwareProject();
            sketchwareProject.setId(Integer.parseInt(Objects.requireNonNull(project.get("sc_id")).toString()));
            sketchwareProject.setProjectName(Objects.requireNonNull(project.get("my_app_name")).toString());
            sketchwareProject.setProjectPackage(Objects.requireNonNull(project.get("my_sc_pkg_name")).toString());
            sketchwareProject.setIconPath(SKETCHWARE_BASE_PATH + "/resources/icons/" + project.get("sc_id") + "/icon.png");
            projects.add(sketchwareProject);
        }
        return projects;
    }
    public ArrayList<BackupedProject> getProjectBackups (String src) {
        // binding array with projects
        ArrayList<BackupedProject> projects = new ArrayList<>();
        // binding array with files in [src]
        ArrayList<String> files = new ArrayList<>();
        // list files to [files]
        FileUtil.listDir(src, files);
        // perform projects list
        for(String file : files) {
            // perform Backuped Projects

            BackupedProject project = JsonUtil.gson.fromJson(FileUtil.readFile(file.concat("/config.scfg")), BackupedProject.class);
            project.setProject(getProject(project.getConfiguration().getListFilePath()));
            // perform SketchwareProjectConfiguration
            project.setConfiguration(project.getConfiguration());
            projects.add(project);
        }
        return projects;
    }



    /*
    *
    * returning array with list backuped projects (by time)
    *
    */
    public ArrayList<ArrayList<BackupedProject>> getBackupedProjects () {
        // init array with array
        ArrayList<ArrayList<BackupedProject>> array = new ArrayList<>();
        // making array with files
        ArrayList<String> files = new ArrayList<>();
        FileUtil.listDir(SKETCHCODE_BACKUP_BASE_PATH, files);
        // adding all to list
        for(String file : files) {
            ArrayList<BackupedProject> backupedProjects = getProjectBackups(file);
            array.add(backupedProjects);
        }
        return array;
    }

    public void backupProject (int projectId) {
        backupProject(projectId, SKETCHCODE_BACKUP_BASE_PATH);
    }

    public void backupProject (int projectId, String sourcePath) {
        SketchwareProjectConfiguration configuration = getConfiguration(projectId);
        BackupedProject backupData = getBackupConfiguration(projectId, sourcePath);
        // writing configuration
        FileUtil.writeFile(sourcePath.concat("/").concat(backupData.getProject().getProjectPackage()).concat("/").concat(String.valueOf(backupData.getTime())).concat("/config.scfg"), new Gson().toJson(backupData));
        // copying sketchware conf to sourcePath/info
        FileUtil.copyFile(configuration.getListFilePath(), backupData.getConfiguration().getListFilePath());
        // copying project
        copyProject(configuration, backupData.getConfiguration());
    }

    private BackupedProject getBackupConfiguration (int forProject, String sourcePath) {
        // making configuration
        BackupedProject configuration = new BackupedProject();
        // get a sketchware project
        SketchwareProject sketchwareProject = getSketchwareProject(forProject);
        // get current time
        long time = System.currentTimeMillis();
        // base folder
        String baseFolder = sourcePath.concat("/").concat(sketchwareProject.getProjectPackage()).concat("/").concat(String.valueOf(time));
        // set backup conf values
        configuration.getConfiguration().setId(forProject);
        configuration.getConfiguration().setBakFolderPath(baseFolder.concat("/bak"));
        configuration.getConfiguration().setListFilePath(baseFolder.concat("/info/project"));
        configuration.getConfiguration().setResources(new SketchwareProjectResources(baseFolder.concat("/resources/icons"), baseFolder.concat("/resources/images"),
                baseFolder.concat("/resources/fonts"), baseFolder.concat("/resources/fonts")));
        configuration.setTime(time);
        configuration.setProject(getSketchwareProject(forProject));
        return configuration;
    }

    public SketchwareProject getSketchwareProject (int projectId) {
        // checking is project exist
        if(!isProjectExist(projectId))
            throw new ProjectNotFoundException(projectId);

        // exec getProject() func for get project
        return getProject(SKETCHWARE_PROJECTS_PATH.concat("/").concat(String.valueOf(projectId)).concat("/project"));
    }

    public SketchwareProject getProject (String srcFile) {
        // checking is file exist, if not throwing exception
        if(!FileUtil.isExistFile(srcFile))
            throw new ProjectNotHaveRequiredInfoException("failed to access file: ".concat(srcFile).concat(" file not found."));
        // decrypting project data
        HashMap<String, Object> project = JsonUtil.from(projectDecryptor.decryptListFile(srcFile));
        // set values
        SketchwareProject sketchwareProject = new SketchwareProject();
        sketchwareProject.setId(Integer.parseInt(Objects.requireNonNull(project.get("sc_id")).toString()));
        sketchwareProject.setProjectName(Objects.requireNonNull(project.get("my_app_name")).toString());
        sketchwareProject.setProjectPackage(Objects.requireNonNull(project.get("my_sc_pkg_name")).toString());
        sketchwareProject.setIconPath(SKETCHWARE_BASE_PATH + "/resources/icons/" + project.get("sc_id") + "/icon.png");
        return sketchwareProject;
    }

    private ProjectDecryptor projectDecryptor = new ProjectDecryptor();

    /*
    *
    * Projects Manager Decryption
    *
    */
    public ProjectDecryptor getDecryptor() {
        return projectDecryptor;
    }

    /*
    *
    * Copy project [id] to new or already exists sketchware project [newId]
    *
    */
    public void copyProject (int id, int newId) {
        SketchwareProjectConfiguration configuration = getConfiguration(id);
        SketchwareProjectConfiguration newConfiguration = getNewConfiguration(newId);
        copyProject(configuration, newConfiguration);
    }

    /*
    *
    * returning configuration for new project
    *
    */
    public SketchwareProjectConfiguration getNewConfiguration (int id) {
        SketchwareProjectConfiguration configuration = new SketchwareProjectConfiguration();
        configuration.setId(id);
        configuration.setResources(new SketchwareProjectResources(SKETCHWARE_RESOURCES_PATH.concat("/icons/").concat(String.valueOf(id)), SKETCHWARE_RESOURCES_PATH.concat("/images/").concat(String.valueOf(id)), SKETCHWARE_RESOURCES_PATH.concat("/fonts/").concat(String.valueOf(id)), SKETCHWARE_RESOURCES_PATH.concat("/sounds/").concat(String.valueOf(id))));
        configuration.setListFilePath(SKETCHWARE_PROJECTS_PATH.concat("/").concat(String.valueOf(id)).concat("/project"));
        configuration.setBakFolderPath(SKETCHWARE_BASE_PATH.concat("/bak/").concat(String.valueOf(id)));
        return configuration;
    }


    /*
    *
    *  return next free id for sketchware project
    *
    */
    public int nextFreeId () {
        int returnValue = 601;
        while (true) {
            if(isProjectExist(returnValue))
                returnValue++;
            else return returnValue;
        }
    }

    public void duplicateProject (int projectId) {
        copyProject(projectId, nextFreeId());
    }

    /*
     *
     * Input [configuration] <- from where it will copy project to [newConfiguration] <- to what it will copy
     *
     */
    public void copyProject (SketchwareProjectConfiguration configuration, SketchwareProjectConfiguration newConfiguration) {
        try {
            // starting copying logic & view data.
            if (configuration.getBakFolderPath() != null)
                if (newConfiguration.getBakFolderPath() != null)
                    CopyManager.copyFolder(new File(configuration.getBakFolderPath()), new File(newConfiguration.getBakFolderPath()));
                else
                    throw new ProjectNotHaveRequiredInfoException("The configuration has a bak path, but the new Configuration one does not have a specified path for copying.");

            // copying resources
            if (configuration.getResources().getIconsPath() != null)
                if (newConfiguration.getResources().getIconsPath() != null)
                    CopyManager.copyFolder(new File(configuration.getResources().getIconsPath()), new File(newConfiguration.getResources().getIconsPath()));
                else
                    throw new ProjectNotHaveRequiredInfoException("The configuration have resources->icons path, but the new Configuration does not have specified path for icons");

            if (configuration.getResources().getFontsPath() != null)
                if (newConfiguration.getResources().getFontsPath() != null)
                    CopyManager.copyFolder(new File(configuration.getResources().getFontsPath()), new File(newConfiguration.getResources().getFontsPath()));
                else
                    throw new ProjectNotHaveRequiredInfoException("The configuration have resources->fonts path, but the new Configuration does not have specified path for fonts.");

            if (configuration.getResources().getImagesPath() != null)
                if (newConfiguration.getResources().getImagesPath() != null)
                    CopyManager.copyFolder(new File(configuration.getResources().getImagesPath()), new File(newConfiguration.getResources().getImagesPath()));
                else
                    throw new ProjectNotHaveRequiredInfoException("The configuration have resources->images path, but the new Configuration does not have specified path for images.");

            if (configuration.getResources().getSoundsPath() != null)
                if (newConfiguration.getResources().getImagesPath() != null)
                    CopyManager.copyFolder(new File(configuration.getResources().getSoundsPath()), new File(newConfiguration.getResources().getSoundsPath()));
                else
                    throw new ProjectNotHaveRequiredInfoException("The configuration have resources->sounds path, but the new Configuration does not have specified path for sounds.");


            if (configuration.getListFilePath() == null)
                throw new ProjectNotHaveRequiredInfoException("configuration does not have a required list file. Maybe you should use validateProject() ?");

            // checking is configuration exists
            if(!FileUtil.isExistFile(configuration.getListFilePath()))
                throw new ProjectNotHaveRequiredInfoException("project list file not exist at path: ".concat(configuration.getListFilePath()));

            // get [configuration] info from list file
            HashMap<String, Object> confListFileInfo = JsonUtil.from(getDecryptor().decryptListFile(configuration.getListFilePath()));

            if (confListFileInfo == null || confListFileInfo.isEmpty())
                throw new ProjectNotHaveRequiredInfoException("configuration have non-exist or bad formatted project list file.");

            // transforming to new project id
            confListFileInfo.put("sc_id", String.valueOf(newConfiguration.getId()));

            System.out.println(SKETCHWARE_PROJECTS_PATH.concat("/").concat(String.valueOf(newConfiguration.getId())));

            // making paths
            FileUtil.makeDir(SKETCHWARE_PROJECTS_PATH.concat("/").concat(String.valueOf(newConfiguration.getId())));

            // writing list info file with new sc_id in new project path
            getDecryptor().writeEncodedListFile(newConfiguration.getListFilePath() , new Gson().toJson(confListFileInfo));
            // end
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public SketchwareProjectConfiguration getConfiguration (int sketchwareProjectId) {
        // checking is project exist
        if (!isProjectExist(sketchwareProjectId))
            throw new ProjectNotFoundException(sketchwareProjectId);
        // making configuration
        SketchwareProjectConfiguration configuration = new SketchwareProjectConfiguration();
        configuration.setBakFolderPath(SKETCHWARE_BASE_PATH.concat("/bak/").concat(String.valueOf(sketchwareProjectId)));
        configuration.setListFilePath(SKETCHWARE_PROJECTS_PATH.concat("/").concat(String.valueOf(sketchwareProjectId)).concat("/project"));
        configuration.setResources(new SketchwareProjectResources(SKETCHWARE_RESOURCES_PATH.concat("/icons/").concat(String.valueOf(sketchwareProjectId)), SKETCHWARE_RESOURCES_PATH.concat("/images/").concat(String.valueOf(sketchwareProjectId)), SKETCHWARE_RESOURCES_PATH.concat("/fonts/").concat(String.valueOf(sketchwareProjectId)),SKETCHWARE_RESOURCES_PATH.concat("/sounds/").concat(String.valueOf(sketchwareProjectId))));
        return configuration;
    }

    public boolean isProjectExist (int projectId) {
        // making boolean response variable
        boolean is = false;
        // Make list with files from SKETCHWARE_PROJECTS_PATH
        ArrayList<String> files = new ArrayList<>();
        FileUtil.listDir(SKETCHWARE_PROJECTS_PATH, files);
        // checking is exist
        for (String file : files) {
            if(Objects.equals(Uri.parse(file).getLastPathSegment(), String.valueOf(projectId))) {
                is = true;
            }
        }
        return is;
    }

    public static class ProjectNotFoundException extends RuntimeException {

        private int id;

        public ProjectNotFoundException(int id) {
            this.id = id;
        }

        public String toString() {
            return "Project with input id " + id + " not found.";
        }

        @Nullable
        @Override
        public String getMessage() {
            return toString();
        }
    }

    public static class ProjectNotHaveRequiredInfoException extends RuntimeException {

        private String msg;

        public ProjectNotHaveRequiredInfoException(String msg)
        {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "ProjectNotHaveRequiredInfoException: " + msg;
        }

        @NonNull
        @Override
        public String getMessage() {
            return msg;
        }

    }

    public static class ProjectDecryptor {
        public String decryptListFile(String path) {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                byte[] arrby = "sketchwaresecure".getBytes();
                // init Cipher for decryption
                cipher.init(2, new SecretKeySpec(arrby, "AES"), new IvParameterSpec(arrby));
                RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");
                byte[] arrby2 = new byte[(int) randomAccessFile.length()];
                randomAccessFile.readFully(arrby2);
                return new String(cipher.doFinal(arrby2));
            } catch (Exception e) {
                e.printStackTrace();
                throw new ProjectCryptorException("failed when trying to get sketchware info from path: ".concat(path));
            }
        }

        public void writeEncodedListFile(String path, String value) {
            try {
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                byte[] arrby = "sketchwaresecure".getBytes();
                // init Cipher for decryption
                cipher.init(1, new SecretKeySpec(arrby, "AES"), new IvParameterSpec(arrby));
                byte[] arrby2 = cipher.doFinal(value.getBytes());
                RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
                randomAccessFile.setLength(0L);
                randomAccessFile.write(arrby2);
            } catch (Exception e) {
                throw new ProjectCryptorException("failed when trying to write sketchware info to path: ".concat(path).concat(". Error: ").concat(e.getMessage()));
            }
        }

        public static class ProjectCryptorException extends RuntimeException {
            private String msg;

            public ProjectCryptorException(String msg)
            {
                this.msg = msg;
            }

            @Override
            public String toString() {
                return "ProjectCryptorException: " + msg;
            }

            @NonNull
            @Override
            public String getMessage() {
                return msg;
            }
        }

    }

}
