package com.feylabs.bermatematika.model.formula

data class FormulaModel(
    var id: String = "",
    var name: String = "",
    var category: String = "",
    var formula: String = "",
    var pdf_path: String = "",
    var created_at: String = "",
    var updated_at: String = "",
    var webview_detail: String = ""
) {
}