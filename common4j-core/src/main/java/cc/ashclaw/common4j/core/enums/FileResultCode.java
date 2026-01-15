// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.core.enums;

/**
 * Provides common file operation result codes and their descriptions.
 * <p>
 * 提供常用的文件操作结果码及其描述。
 * <p>
 * Includes result codes for file operations, directory operations, IO operations, and file-related errors.
 * <p>
 * 包含文件操作、目录操作、IO操作和文件相关错误的结果码。
 *
 * @author b1itz7
 * @since 1.0.0
 */
public enum FileResultCode {

    
    /**
     * 200 File Created Successfully
     * <p>
     * 200 文件创建成功
     */
    FILE_CREATED_SUCCESS(200, "File Created Successfully"),

    /**
     * 200 File Read Successfully
     * <p>
     * 200 文件读取成功
     */
    FILE_READ_SUCCESS(200, "File Read Successfully"),

    /**
     * 200 File Written Successfully
     * <p>
     * 200 文件写入成功
     */
    FILE_WRITTEN_SUCCESS(200, "File Written Successfully"),

    /**
     * 200 File Deleted Successfully
     * <p>
     * 200 文件删除成功
     */
    FILE_DELETED_SUCCESS(200, "File Deleted Successfully"),

    /**
     * 200 File Copied Successfully
     * <p>
     * 200 文件复制成功
     */
    FILE_COPIED_SUCCESS(200, "File Copied Successfully"),

    /**
     * 200 File Moved Successfully
     * <p>
     * 200 文件移动成功
     */
    FILE_MOVED_SUCCESS(200, "File Moved Successfully"),

    /**
     * 200 File Renamed Successfully
     * <p>
     * 200 文件重命名成功
     */
    FILE_RENAMED_SUCCESS(200, "File Renamed Successfully"),

    /**
     * 200 File Uploaded Successfully
     * <p>
     * 200 文件上传成功
     */
    FILE_UPLOADED_SUCCESS(200, "File Uploaded Successfully"),

    /**
     * 200 File Downloaded Successfully
     * <p>
     * 200 文件下载成功
     */
    FILE_DOWNLOADED_SUCCESS(200, "File Downloaded Successfully"),

    /**
     * 200 File Compressed Successfully
     * <p>
     * 200 文件压缩成功
     */
    FILE_COMPRESSED_SUCCESS(200, "File Compressed Successfully"),

    /**
     * 200 File Decompressed Successfully
     * <p>
     * 200 文件解压成功
     */
    FILE_DECOMPRESSED_SUCCESS(200, "File Decompressed Successfully"),

    
    /**
     * 200 Directory Created Successfully
     * <p>
     * 200 目录创建成功
     */
    DIRECTORY_CREATED_SUCCESS(200, "Directory Created Successfully"),

    /**
     * 200 Directory Listed Successfully
     * <p>
     * 200 目录列出成功
     */
    DIRECTORY_LISTED_SUCCESS(200, "Directory Listed Successfully"),

    /**
     * 200 Directory Deleted Successfully
     * <p>
     * 200 目录删除成功
     */
    DIRECTORY_DELETED_SUCCESS(200, "Directory Deleted Successfully"),

    /**
     * 200 Directory Copied Successfully
     * <p>
     * 200 目录复制成功
     */
    DIRECTORY_COPIED_SUCCESS(200, "Directory Copied Successfully"),

    /**
     * 200 Directory Moved Successfully
     * <p>
     * 200 目录移动成功
     */
    DIRECTORY_MOVED_SUCCESS(200, "Directory Moved Successfully"),

    
    /**
     * 404 File Not Found
     * <p>
     * 404 文件不存在
     */
    FILE_NOT_FOUND(404, "File Not Found"),

    /**
     * 409 File Already Exists
     * <p>
     * 409 文件已存在
     */
    FILE_ALREADY_EXISTS(409, "File Already Exists"),

    /**
     * 400 Invalid File Path
     * <p>
     * 400 无效的文件路径
     */
    INVALID_FILE_PATH(400, "Invalid File Path"),

    /**
     * 400 Invalid File Name
     * <p>
     * 400 无效的文件名
     */
    INVALID_FILE_NAME(400, "Invalid File Name"),

    /**
     * 400 File Size Exceeds Limit
     * <p>
     * 400 文件大小超出限制
     */
    FILE_SIZE_EXCEEDS_LIMIT(400, "File Size Exceeds Limit"),

    /**
     * 400 Invalid File Format
     * <p>
     * 400 无效的文件格式
     */
    INVALID_FILE_FORMAT(400, "Invalid File Format"),

    /**
     * 400 File Extension Not Allowed
     * <p>
     * 400 文件扩展名不允许
     */
    FILE_EXTENSION_NOT_ALLOWED(400, "File Extension Not Allowed"),

    /**
     * 403 File Permission Denied
     * <p>
     * 403 文件权限不足
     */
    FILE_PERMISSION_DENIED(403, "File Permission Denied"),

    /**
     * 400 File Is Empty
     * <p>
     * 400 文件为空
     */
    FILE_IS_EMPTY(400, "File Is Empty"),

    /**
     * 400 File Is Read Only
     * <p>
     * 400 文件为只读
     */
    FILE_IS_READ_ONLY(400, "File Is Read Only"),

    /**
     * 400 File Is Locked
     * <p>
     * 400 文件被锁定
     */
    FILE_IS_LOCKED(400, "File Is Locked"),

    /**
     * 400 File Is Corrupted
     * <p>
     * 400 文件已损坏
     */
    FILE_IS_CORRUPTED(400, "File Is Corrupted"),

    
    /**
     * 404 Directory Not Found
     * <p>
     * 404 目录不存在
     */
    DIRECTORY_NOT_FOUND(404, "Directory Not Found"),

    /**
     * 409 Directory Already Exists
     * <p>
     * 409 目录已存在
     */
    DIRECTORY_ALREADY_EXISTS(409, "Directory Already Exists"),

    /**
     * 400 Invalid Directory Path
     * <p>
     * 400 无效的目录路径
     */
    INVALID_DIRECTORY_PATH(400, "Invalid Directory Path"),

    /**
     * 400 Invalid Directory Name
     * <p>
     * 400 无效的目录名
     */
    INVALID_DIRECTORY_NAME(400, "Invalid Directory Name"),

    /**
     * 403 Directory Permission Denied
     * <p>
     * 403 目录权限不足
     */
    DIRECTORY_PERMISSION_DENIED(403, "Directory Permission Denied"),

    /**
     * 400 Directory Is Not Empty
     * <p>
     * 400 目录不为空
     */
    DIRECTORY_IS_NOT_EMPTY(400, "Directory Is Not Empty"),

    
    /**
     * 500 IO Exception
     * <p>
     * 500 IO异常
     */
    IO_EXCEPTION(500, "IO Exception"),

    /**
     * 500 File Read Error
     * <p>
     * 500 文件读取错误
     */
    FILE_READ_ERROR(500, "File Read Error"),

    /**
     * 500 File Write Error
     * <p>
     * 500 文件写入错误
     */
    FILE_WRITE_ERROR(500, "File Write Error"),

    /**
     * 500 File Copy Error
     * <p>
     * 500 文件复制错误
     */
    FILE_COPY_ERROR(500, "File Copy Error"),

    /**
     * 500 File Move Error
     * <p>
     * 500 文件移动错误
     */
    FILE_MOVE_ERROR(500, "File Move Error"),

    /**
     * 500 File Delete Error
     * <p>
     * 500 文件删除错误
     */
    FILE_DELETE_ERROR(500, "File Delete Error"),

    /**
     * 500 Directory Create Error
     * <p>
     * 500 目录创建错误
     */
    DIRECTORY_CREATE_ERROR(500, "Directory Create Error"),

    /**
     * 500 Directory Delete Error
     * <p>
     * 500 目录删除错误
     */
    DIRECTORY_DELETE_ERROR(500, "Directory Delete Error"),

    /**
     * 500 Out Of Disk Space
     * <p>
     * 500 磁盘空间不足
     */
    OUT_OF_DISK_SPACE(500, "Out Of Disk Space"),

    /**
     * 500 File Upload Error
     * <p>
     * 500 文件上传错误
     */
    FILE_UPLOAD_ERROR(500, "File Upload Error"),

    /**
     * 500 File Download Error
     * <p>
     * 500 文件下载错误
     */
    FILE_DOWNLOAD_ERROR(500, "File Download Error"),

    /**
     * 500 File Compression Error
     * <p>
     * 500 文件压缩错误
     */
    FILE_COMPRESSION_ERROR(500, "File Compression Error"),

    /**
     * 500 File Decompression Error
     * <p>
     * 500 文件解压错误
     */
    FILE_DECOMPRESSION_ERROR(500, "File Decompression Error"),

    
    /**
     * 500 File Operation Timeout
     * <p>
     * 500 文件操作超时
     */
    FILE_OPERATION_TIMEOUT(500, "File Operation Timeout"),

    /**
     * 500 File Encryption Error
     * <p>
     * 500 文件加密错误
     */
    FILE_ENCRYPTION_ERROR(500, "File Encryption Error"),

    /**
     * 500 File Decryption Error
     * <p>
     * 500 文件解密错误
     */
    FILE_DECRYPTION_ERROR(500, "File Decryption Error"),

    /**
     * 500 File Checksum Mismatch
     * <p>
     * 500 文件校验和不匹配
     */
    FILE_CHECKSUM_MISMATCH(500, "File Checksum Mismatch"),

    /**
     * 500 File Not Supported
     * <p>
     * 500 文件不支持
     */
    FILE_NOT_SUPPORTED(500, "File Not Supported");

    private final int code;
    private final String message;

    /**
     * Creates a new file result code.
     * <p>
     * 创建一个新的文件结果码。
     *
     * @param code    the result code
     * @param message the result message
     */
    FileResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets the result code.
     * <p>
     * 获取结果码。
     *
     * @return the result code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets the result message.
     * <p>
     * 获取结果消息。
     *
     * @return the result message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Finds a file result code by its code.
     * <p>
     * 根据结果码查找文件结果。
     *
     * @param code the result code
     * @return the corresponding file result code, or null if not found
     */
    public static FileResultCode valueOf(int code) {
        for (FileResultCode resultCode : values()) {
            if (resultCode.code == code) {
                return resultCode;
            }
        }
        return null;
    }
}