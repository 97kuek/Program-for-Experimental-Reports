#!/usr/bin/env bash
set -euo pipefail

README="README.md"
TITLE="${TITLE:-Repository Structure}"
START="<!-- structure-start -->"
END="<!-- structure-end -->"
DEPTH="${STRUCTURE_DEPTH:-2}"
EXCLUDES="${EXCLUDES:-.git|.github}"

# ツリー生成（隠し含む、除外、ディレクトリ優先）
STRUCTURE=$(LC_ALL=C tree -a -L "${DEPTH}" -I "${EXCLUDES}" -F --dirsfirst || true)
: "${STRUCTURE:=.(empty)}"

# README に入れるコードブロック
BLOCK=$(printf '```text\n%s\n```\n' "$STRUCTURE")

SECTION=$(cat <<EOF
## ${TITLE}

${START}
${BLOCK}
${END}
EOF
)

# README が無ければ作る
if [[ ! -f "${README}" ]]; then
{
    echo "# ${TITLE}"
    echo
    echo "${START}"
    echo "${BLOCK}"
    echo "${END}"
    echo
} > "${README}"
    echo "Created ${README} with structure section."
    exit 0
fi

# 既存ブロックがあれば置換、無ければ追記
if grep -q "${START}" "${README}" && grep -q "${END}" "${README}"; then
    awk -v start="${START}" -v end="${END}" -v repl="${BLOCK}" '
    BEGIN{inblk=0}
    {
        if ($0 ~ start) {print start; print repl; found=1; inblk=1; next}
        if ($0 ~ end)   {print end; inblk=0; next}
        if (!inblk) print
    }
    END{
        if (!found) {
            print start "\n" repl "\n" end
        }
    }
    ' "${README}" > "${README}.tmp"
    mv "${README}.tmp" "${README}"
    echo "Replaced structure block in ${README}."
else
    {
        echo
        echo "${SECTION}"
        echo
    } >> "${README}"
    echo "Appended structure section to ${README}."
fi
