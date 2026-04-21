<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<body style="margin:0;padding:0;background:#f4f5f7;font-family:-apple-system,BlinkMacSystemFont,'Segoe UI',Roboto,sans-serif;">
<table width="100%" cellpadding="0" cellspacing="0" style="padding:40px 0;">
    <tr><td align="center">
        <table width="420" cellpadding="0" cellspacing="0" style="background:#fff;border-radius:12px;box-shadow:0 2px 12px rgba(0,0,0,.06);overflow:hidden;">
            <tr><td style="background:linear-gradient(135deg,#6366f1,#818cf8);padding:28px 32px;text-align:center;">
                <h1 style="margin:0;color:#fff;font-size:20px;font-weight:600;letter-spacing:1px;">${title}</h1>
            </td></tr>
            <tr><td style="padding:32px;">
                <p style="margin:0 0 8px;color:#64748b;font-size:14px;">您好，您正在进行账户操作，验证码为：</p>
                <div style="margin:20px 0;text-align:center;">
                    <span style="display:inline-block;padding:14px 36px;background:#f8fafc;border:2px dashed #e2e8f0;border-radius:10px;font-size:28px;font-weight:700;letter-spacing:6px;color:#6366f1;">${code}</span>
                </div>
                <p style="margin:0;color:#94a3b8;font-size:12px;line-height:1.6;">此验证码仅用于本次操作，请勿泄露给他人。<br/>如非本人操作，请忽略此邮件。</p>
            </td></tr>
            <tr><td style="padding:16px 32px;background:#fafbfc;border-top:1px solid #f1f5f9;text-align:center;">
                <p style="margin:0;color:#cbd5e1;font-size:11px;">此邮件由系统自动发送，请勿回复</p>
            </td></tr>
        </table>
    </td></tr>
</table>
</body>
</html>
