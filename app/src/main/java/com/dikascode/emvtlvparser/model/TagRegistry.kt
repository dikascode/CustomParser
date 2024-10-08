package com.dikascode.emvtlvparser.model

object TagRegistry {
    private val emvTags = setOf(
        "9F02", "5F2A", "9F1A", "9F36", "82", "4F", "50", "57", "5A", "5F20",
        "5F24", "5F28", "5F34", "06", "41", "42", "43", "44", "45", "46",
        "47", "48", "4D", "51", "52", "53", "56", "58", "59", "5B", "5C",
        "5E", "5F21", "5F22", "5F23", "5F25", "5F26", "5F27", "5F29", "5F2B",
        "5F2C", "5F2E", "5F2F", "5F30", "5F32", "5F33", "5F35", "5F36", "5F37",
        "5F38", "5F39", "5F3A", "5F3B", "5F3C", "5F3D", "5F40", "5F41", "5F42",
        "5F43", "5F44", "5F45", "5F46", "5F47", "5F48", "5F49", "5F4A", "5F4C",
        "5F4D", "5F4E", "5F50", "5F53", "5F54", "5F55", "5F56", "5F57", "60",
        "6080", "6081", "6082", "6083", "6084", "6085", "60A0", "61", "62",
        "6280", "6281", "6282", "6283", "6284", "6285", "6286", "6287", "6288",
        "628A", "628B", "628C", "628D", "62A0", "62A1", "62A2", "62A5", "62AB",
        "62AC", "63", "64", "65", "66", "67", "68", "6A", "6A80", "6A81",
        "6A82", "6A83", "6A84", "6B", "6B06", "6B80", "6BA0", "6C", "6D",
        "6E", "6F", "6FA5", "70", "71", "7186", "719F18", "72", "73", "77",
        "78", "79", "7A", "7A80", "7A81", "7A82", "7A83", "7A84", "7A85",
        "7A86", "7A87", "7A88", "7A89", "7A8A", "7A8B", "7A8C", "7A8D", "7A8E",
        "7A93", "7B", "7B80", "7B8A", "7BA4", "7BAA", "7BAC", "7BB4", "7BB6",
        "7BB8", "7D", "7D80", "7D81", "7D82", "7D83", "7D84", "7D85", "7D86",
        "7D87", "7D8E", "7D90", "7D91", "7D92", "7D93", "7D94", "7D95", "7D96",
        "7D97", "7D99", "7D9A", "7D9B", "7D9C", "7D9D", "7D9E", "7DA0", "7DA1",
        "7DA2", "7DA4", "7DA5", "7DA8", "7DAA", "7DAB", "7DAC", "7DAD", "7DAE",
        "7DAF", "7DB0", "7DB1", "7DB2", "7DB3", "7DB4", "7DB5", "7DB6", "7DB7",
        "7DB8", "7DB9", "7DBA", "7DBB", "7DBC", "7DBD", "7DBE", "7E", "7F20",
        "7F21", "7F2E", "7F49", "7F4980", "7F4981", "7F4982", "7F4983", "7F4984",
        "7F4985", "7F4986", "7F4C", "7F4E", "7F4E42", "7F4E65", "7F60", "80",
        "81", "82", "83", "84", "86", "87", "88", "89", "8A", "8C", "8D",
        "8E", "8F", "90", "91", "92", "93", "94", "95", "97", "98", "99",
        "9A", "9B", "9C", "9D", "9F01", "9F03", "9F04", "9F05", "9F06",
        "9F07", "9F08", "9F09", "9F0A", "9F0B", "9F0D", "9F0E", "9F0F", "9F10",
        "9F11", "9F12", "9F13", "9F14", "9F15", "9F16", "9F17", "9F18", "9F19",
        "9F1B", "9F1C", "9F1D", "9F1E", "9F1F", "9F20", "9F21", "9F22", "9F23",
        "9F24", "9F25", "9F26", "9F27", "9F29", "9F2A", "9F2D", "9F2E", "9F2F",
        "9F32", "9F33", "9F34", "9F35", "9F36", "9F37", "9F38", "9F39", "9F3A",
        "9F3B", "9F3C", "9F3D", "9F40", "9F41", "9F42", "9F43", "9F44", "9F45",
        "9F46", "9F47", "9F48", "9F49", "9F4A", "9F4B", "9F4C", "9F4D", "9F4E",
        "9F4F", "9F50", "9F51", "9F52", "9F53", "9F54", "9F55", "9F56", "9F57",
        "9F58", "9F59", "9F5A", "9F5B", "9F5C", "9F5D", "9F5E", "9F5F", "9F60",
        "9F61", "9F62", "9F63", "9F64", "9F65", "9F66", "9F67", "9F68", "9F69",
        "9F6A", "9F6B", "9F6C", "9F6D", "9F6E", "9F6F", "9F70", "9F71", "9F72",
        "9F73", "9F74", "9F75", "9F76", "9F77", "9F78", "9F79", "9F7A", "9F7B",
        "9F7C", "9F7D", "9F7E", "9F7F", "A5", "BF0C", "BF50", "BF60", "C3",
        "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CD", "CE", "CF",
        "D1", "D2", "D3", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC",
        "DD"
    )

    fun isKnownTagRetrieved(tag: String): Boolean {
        return emvTags.contains(tag)
    }
}