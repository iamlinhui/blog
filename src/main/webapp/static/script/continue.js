window.setInterval(function () {
    $.ajax({
        url: "admin/continue",
        type: "OPTIONS",
        data: {time: Math.floor(Math.random() * 100)},
        success: function (msg) {
            if (msg.code !== 200) {
                window.location.reload();
            }
        },
        dataType: "json"
    });
}, 1800000);

$(function () {
    editormd("editor", {
        name: 'postContent',
        autoWidth: true,
        height: '900px',
        syncScrolling: "single",
        placeholder: '',
        path: "static/editor/lib/",
        pluginPath: "static/editor/plugins/",
        imageUpload: true,
        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL: "/admin/upload",
        taskList: true,
        htmlDecode: "style,script,iframe|on*",
        emoji: false,
        tex: true,
        flowChart: true,
        sequenceDiagram: true
    });
});
