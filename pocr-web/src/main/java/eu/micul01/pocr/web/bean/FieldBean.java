package eu.micul01.pocr.web.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FieldBean implements Serializable {

	@Override
	public String toString() {
		return "FieldBean [fieldName=" + fieldName + ", fieldType=" + fieldType
				+ ", description=" + description + ", isRequired=" + isRequired
				+ ", dependencies=" + dependencies + "]";
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String fieldName;
	private String fieldType;
	private String description;
	private boolean isRequired;
	private List<FieldBean> dependencies;

	public FieldBean() {
		initialize();
	}

	public void initialize() {
		fieldName = "";
		fieldType = "";
		description = "";
		dependencies = new ArrayList<FieldBean>();
		isRequired = false;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(final String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(final String fieldType) {
		this.fieldType = fieldType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(final boolean isRequired) {
		this.isRequired = isRequired;
	}

	public List<FieldBean> getDependencies() {
		return dependencies;
	}

	public void setDependencies(final List<FieldBean> dependencies) {
		this.dependencies = dependencies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (dependencies == null ? 0 : dependencies.hashCode());
		result = prime * result
				+ (description == null ? 0 : description.hashCode());
		result = prime * result
				+ (fieldName == null ? 0 : fieldName.hashCode());
		result = prime * result
				+ (fieldType == null ? 0 : fieldType.hashCode());
		result = prime * result + (isRequired ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final FieldBean other = (FieldBean) obj;
		if (dependencies == null) {
			if (other.dependencies != null) {
				return false;
			}
		} else if (!dependencies.equals(other.dependencies)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (fieldName == null) {
			if (other.fieldName != null) {
				return false;
			}
		} else if (!fieldName.equals(other.fieldName)) {
			return false;
		}
		if (fieldType == null) {
			if (other.fieldType != null) {
				return false;
			}
		} else if (!fieldType.equals(other.fieldType)) {
			return false;
		}
		if (isRequired != other.isRequired) {
			return false;
		}
		return true;
	}

}
