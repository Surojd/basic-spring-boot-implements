package com.techinherit.language.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "language_key")
@NamedQueries({
    @NamedQuery(name = "LanguageKey.findAll", query = "SELECT l FROM LanguageKey l")})
public class LanguageKey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "key_name")
    private String keyName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "keyId")
    @JsonIgnore
    private List<LanguageText> languageTextList;

    public LanguageKey() {
    }

    public LanguageKey(Integer id) {
        this.id = id;
    }

    public LanguageKey(Integer id, String keyName) {
        this.id = id;
        this.keyName = keyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public List<LanguageText> getLanguageTextList() {
        return languageTextList;
    }

    public void setLanguageTextList(List<LanguageText> languageTextList) {
        this.languageTextList = languageTextList;
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
        if (!(object instanceof LanguageKey)) {
            return false;
        }
        LanguageKey other = (LanguageKey) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.techinherit.tracking.language.model.LanguageKey[ id=" + id + " ]";
    }

}
