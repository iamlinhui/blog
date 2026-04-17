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

var vditorInstance;

$(function () {
    var initialContent = $('#postContent').val() || '';
    vditorInstance = new Vditor('vditor', {
        height: 700,
        mode: 'ir',
        placeholder: '开始写作...',
        toolbarConfig: { pin: true },
        toolbar: [
            'emoji', 'headings', 'bold', 'italic', 'strike', '|',
            'line', 'quote', 'list', 'ordered-list', 'check', 'outdent', 'indent', '|',
            'code', 'inline-code', 'insert-after', 'insert-before', '|',
            'upload', 'link', 'table', '|',
            'undo', 'redo', '|',
            'edit-mode', 'both', 'preview', 'outline', 'code-theme', 'content-theme', '|',
            'fullscreen', 'export', 'help'
        ],
        preview: {
            markdown: { toc: true },
            hljs: { lineNumber: true },
            math: { engine: 'KaTeX' },
            mermaid: { enable: true }
        },
        upload: {
            url: 'admin/upload',
            fieldName: 'file',
            max: 10 * 1024 * 1024,
            accept: 'image/jpg,image/jpeg,image/gif,image/png,image/bmp,image/webp',
            format: function (files, responseText) {
                var res = JSON.parse(responseText);
                if (res.success === 1) {
                    return JSON.stringify({
                        msg: '',
                        code: 0,
                        data: { errFiles: [], succMap: { 'image': res.url } }
                    });
                } else {
                    return JSON.stringify({ msg: res.message, code: 1, data: { errFiles: ['image'], succMap: {} } });
                }
            }
        },
        value: initialContent,
        cache: { enable: false },
        after: function () {
            // Sync content to hidden textarea on form submit
            $('form').on('submit', function () {
                $('#postContent').val(vditorInstance.getValue());
            });
        }
    });
});
