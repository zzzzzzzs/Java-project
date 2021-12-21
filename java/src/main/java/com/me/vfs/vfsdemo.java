package com.me.vfs;


import org.apache.commons.vfs2.FileContent;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.impl.DefaultFileSystemManager;

/**
 * @author zs
 * @date 2021/12/17
 */
public class vfsdemo {
    public static void main(String[] args) throws FileSystemException {
        // 获取文件系统管理器，它是统一API入口
        final DefaultFileSystemManager manager = (DefaultFileSystemManager) VFS.getManager();
        //        manager.addProvider("http", new HttpFileProvider());
        //        manager.addProvider("sftp", new SftpFileProvider());

        /* 1. 本地文件，必须是绝对路径 */
        final FileObject fileObject = manager.resolveFile("file:///your/path/a.txt");
        final FileContent content = fileObject.getContent();
        System.out.println(content);
    }
}
