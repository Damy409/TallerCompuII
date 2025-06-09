## Cómo subir cambios a GitHub desde Linux

### 1. Verifica el estado de tu repositorio local

```bash
git status
```

*Explicación:* Muestra los archivos modificados, los que están listos para agregar y si tienes cambios sin confirmar.

---

### 2. Agrega los archivos que quieres subir (puede ser todos o solo algunos)

Para agregar **todos** los archivos modificados:

```bash
git add .
```

Para agregar archivos específicos:

```bash
git add nombre_del_archivo.ext
```

*Explicación:* Este comando prepara los archivos para ser confirmados (commit).

---

### 3. Crea un commit con un mensaje descriptivo

```bash
git commit -m "Mensaje claro que explique los cambios realizados"
```

*Explicación:* Guarda los cambios localmente con un mensaje que describa qué hiciste.

---

### 4. Trae los últimos cambios del repositorio remoto (para evitar conflictos)

```bash
git pull origin master
```

*Explicación:* Descarga y combina los cambios que otros hayan subido antes que tú para evitar perder trabajo.

Si quieres evitar un commit extra de merge, puedes hacer:

```bash
git pull --rebase origin master
```

Esto aplicará tus cambios después de los que hay en remoto.

---

### 5. Sube tus cambios al repositorio remoto

```bash
git push origin master
```

*Explicación:* Envía tus commits locales al repositorio en GitHub.

---

### Resumen completo para copiar y pegar

```bash
git status
git add .
git commit -m "Describe brevemente los cambios realizados"
git pull origin master
# o para rebase: git pull --rebase origin master
git push origin master
```

---

### Nota importante:

* Si durante el `git pull` hay conflictos, Git te avisará y tendrás que resolverlos manualmente en los archivos indicados, luego hacer:

```bash
git add archivo_resuelto
git commit -m "Resuelvo conflictos"
git push origin master
```
