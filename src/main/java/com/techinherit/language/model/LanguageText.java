package com.techinherit.language.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "language_text")
@NamedQueries({
    @NamedQuery(name = "LanguageText.findAll", query = "SELECT l FROM LanguageText l")})
public class LanguageText implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "text")
    private byte[] text;
    @JoinColumn(name = "lang_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Language langId;
    @JoinColumn(name = "key_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private LanguageKey keyId;

    public LanguageText() {
    }

    public LanguageText(Integer id) {
        this.id = id;
    }

    public LanguageText(Integer id, byte[] text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        this.text = text;
    }

    public Language getLangId() {
        return langId;
    }

    public void setLangId(Language langId) {
        this.langId = langId;
    }

    public LanguageKey getKeyId() {
        return keyId;
    }

    public void setKeyId(LanguageKey keyId) {
        this.keyId = keyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LanguageText)) {
            return false;
        }
        LanguageText other = (LanguageText) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.techinherit.tracking.language.model.LanguageText[ id=" + id + " ]";
    }

}
