package qualite_log.util;

import java.util.List;
import java.util.regex.Pattern;

import javafx.scene.control.TextField;
import javafx.util.Pair;

public class FxUtil {

    /**
     * Ajoute un écouteur de changement de texte à un TextField.
     * Si le texte correspond à l'expression régulière, le style d'erreur est retiré.
     *
     * @param textField Le TextField à surveiller.
     * @param regex L'expression régulière à utiliser pour la validation.
     */
    public static void addTextChangeListener(TextField textField, String regex) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (Pattern.matches(regex, newValue)) {
                textField.getStyleClass().removeAll("text-field-error");
            }
        });
    }

    /**
     * Met à jour le style d'un TextField en fonction de la validité de son contenu.
     *
     * @param textField Le TextField à mettre à jour.
     * @param isValid La validité du contenu du TextField.
     */
    public static void updateTextFieldStyle(TextField textField, boolean isValid) {
        if (isValid) {
            textField.getStyleClass().removeAll("text-field-error");
        } else {
            if (!textField.getStyleClass().contains("text-field-error")) {
                textField.getStyleClass().add("text-field-error");
            }
        }
    }

    public static boolean validateInputs(List<Pair<TextField, String>> fieldRegexPairs) {
        boolean isValid = true;
        for (Pair<TextField, String> pair : fieldRegexPairs) {
            TextField field = pair.getKey();
            String regex = pair.getValue();
            boolean fieldValid = Pattern.matches(regex, field.getText());
            updateTextFieldStyle(field, fieldValid);
            if (!fieldValid) {
                isValid = false;
            }
        }
        return isValid;
    }
}
