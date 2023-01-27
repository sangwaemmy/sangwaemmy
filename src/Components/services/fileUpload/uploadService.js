import React, { Component } from 'react'
import http from "../../../httpComponent"


class uploadService extends Component {
    upload(file, onUploadProgress) {
        let formData = new FormData();

        formData.append("file", file);

        return http.post("/upload", formData, {
            headers: {
                "Content-Type": "multipart/form-data",
            },
            onUploadProgress,
        });
    }

    getFiles() {
        return http.get("/files");
    }
}

export default uploadService