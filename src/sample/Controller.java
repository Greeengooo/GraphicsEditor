package sample;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import jfxtras.labs.util.event.MouseControlUtil;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class Controller  {

    @FXML
    private Canvas canvas;

    @FXML
    private CheckBox erase;

    @FXML
    private TextField  brushSize;

    @FXML
    private TextField shapeSize_WIDTH;

    @FXML
    private TextField shapeSize_HEIGHT;

    @FXML
    private TextField shapeSize_RADIUS;

    @FXML
    private TextField shapeSize_STROKE;

    @FXML
    private Button clearInput;

    @FXML
    private Button clearAll;

    @FXML
    private ColorPicker colorPicker_BRUSH;

    @FXML
    private ColorPicker colorPicker_SHAPES_FILL;

    @FXML
    private ColorPicker colorPicker_SHAPES_STROKE;

    @FXML
    private ImageView rectangle;

    @FXML
    private ImageView circle;

    @FXML
    private ImageView ellipse;

    @FXML
    private ImageView triangle;

    @FXML
    private ImageView textBox;

    @FXML
    private ImageView line;

    @FXML
    private ImageView pentagon;

    @FXML
    private ImageView addImage;

    @FXML
    private Pane pane;

    @FXML
    private ScrollPane scrollPane;


    public void initialize() {
        checkInput();
        draw();
    }

    public void onRectButton() {

        Shape rect = new Rectangle(Integer.parseInt(shapeSize_WIDTH.getText()), Integer.parseInt(shapeSize_HEIGHT.getText()));
        rect.setStrokeWidth(Integer.parseInt(shapeSize_STROKE.getText()));
        rect.setStroke(colorPicker_SHAPES_STROKE.getValue());
        rect.setFill(colorPicker_SHAPES_FILL.getValue());
        rect.setLayoutX(pane.getWidth() / 2 - (Integer.parseInt(shapeSize_WIDTH.getText())/2));
        rect.setLayoutY(pane.getHeight() / 2 - (Integer.parseInt(shapeSize_HEIGHT.getText())/2));

        MouseControlUtil.makeDraggable(rect);
        pane.getChildren().add(rect);
        //Delete shape
        rect.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                pane.getChildren().remove(rect);
            }
        });
    }

    public void onCircButton() {
        Shape circle = new Circle(Integer.parseInt(shapeSize_RADIUS.getText()));
        circle.setStrokeWidth(Integer.parseInt(shapeSize_STROKE.getText()));
        circle.setStroke(colorPicker_SHAPES_STROKE.getValue());
        circle.setFill(colorPicker_SHAPES_FILL.getValue());
        circle.setLayoutX(Integer.parseInt(shapeSize_RADIUS.getText()) + 10);
        circle.setLayoutY(Integer.parseInt(shapeSize_RADIUS.getText()) + 10);

        MouseControlUtil.makeDraggable(circle);
        pane.getChildren().add(circle);
        circle.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                pane.getChildren().remove(circle);
            }
        });
    }

    public void onElipButton() {
        Shape ellipse = new Ellipse(Integer.parseInt(shapeSize_WIDTH.getText()), Integer.parseInt(shapeSize_HEIGHT.getText()));
        ellipse.setStrokeWidth(Integer.parseInt(shapeSize_STROKE.getText()));
        ellipse.setStroke(colorPicker_SHAPES_STROKE.getValue());
        ellipse.setFill(colorPicker_SHAPES_FILL.getValue());
        ellipse.setLayoutX(pane.getWidth() / 2 - (Integer.parseInt(shapeSize_WIDTH.getText())/2));
        ellipse.setLayoutY(pane.getHeight() / 2 - (Integer.parseInt(shapeSize_HEIGHT.getText())/2));


        MouseControlUtil.makeDraggable(ellipse);
        pane.getChildren().add(ellipse);
        ellipse.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                pane.getChildren().remove(ellipse);
            }
        });
    }

    public void onTriButton() {
        double width = Double.parseDouble(shapeSize_WIDTH.getText());
        double height =  Double.parseDouble(shapeSize_HEIGHT.getText());
        Shape polygon = new Polygon(0.0, 0.0, (width)/2, height, width, 0.0);
        Group triangle = new Group(polygon);

        polygon.setStrokeWidth(Integer.parseInt(shapeSize_STROKE.getText()));
        polygon.setStroke(colorPicker_SHAPES_STROKE.getValue());
        polygon.setFill(colorPicker_SHAPES_FILL.getValue());
        polygon.setLayoutX(0);
        polygon.setLayoutY(0);
        triangle.setRotate(180.0);

        MouseControlUtil.makeDraggable(triangle);
        pane.getChildren().add(triangle);
        triangle.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                pane.getChildren().remove(triangle);
            }
        });
    }

    public void onTextBoxButton() {
        TextArea area = new TextArea();
        area.setPrefWidth(Integer.parseInt(shapeSize_WIDTH.getText()));
        area.setPrefHeight(Integer.parseInt(shapeSize_HEIGHT.getText()));
        area.setStyle("-fx-border-width: 5");

        Pane textPane = new Pane();
        Circle movePoint = new Circle();

        if (textPane.isPressed()){
        movePoint.setRadius(10);
        movePoint.setStyle("-fx-background-color: white; -fx-border-color: black");
        movePoint.setLayoutX(area.getWidth());
        movePoint.setLayoutY(area.getHeight());
        textPane.getChildren().add(movePoint);
        }

        textPane.setPrefWidth(area.getPrefWidth() + 60);
        textPane.setPrefHeight(area.getPrefHeight() + 60);
        textPane.getChildren().add(area);
        textPane.setLayoutX(50);

        pane.getChildren().add(textPane);
        MouseControlUtil.makeDraggable(textPane);
        textPane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                pane.getChildren().remove(textPane);
            }
        });
    }

    public void onLineButton() {
        Shape line = new Line(50, 50, Integer.parseInt(shapeSize_WIDTH.getText()), Integer.parseInt(shapeSize_HEIGHT.getText()));
       line.setStroke(colorPicker_SHAPES_FILL.getValue());

        Pane linePane = new Pane();
        linePane.getChildren().add(line);

        pane.getChildren().add(linePane);
        MouseControlUtil.makeDraggable(linePane);
        linePane.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                pane.getChildren().remove(linePane);
            }
        });
    }

    public void onAddImageButton() {
        ImageView img_to_add = new ImageView();
        img_to_add.setX(100);
        img_to_add.setY(100);
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            img_to_add.setImage(image);
        }  catch (IOException ex) {
           ex.printStackTrace();
        }

        if (img_to_add.getImage().getWidth() > pane.getWidth() && img_to_add.getImage().getHeight() > pane.getHeight()) {
            scrollPane.setContent(img_to_add);
            scrollPane.setPrefSize(pane.getWidth(), pane.getHeight());
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            scrollPane.setVisible(true);
        }else {
            pane.getChildren().add(img_to_add);
        }
        MouseControlUtil.makeDraggable(img_to_add);
        img_to_add.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                pane.getChildren().remove(img_to_add);
            }
        });
}

    public void onPentButton() {
        Polygon polygon = new Polygon();

        double width = Double.parseDouble(shapeSize_WIDTH.getText()) - 100;
        double height =  Double.parseDouble(shapeSize_HEIGHT.getText()) - 100;
        polygon.getPoints().setAll(
               0.0, height/1.5,
               width/2, height/4,
               width, height/4,
               width + width/2, height/1.5,
                (width/2 + (width/2 + width/2))/2, height
        );
        Group pentagon = new Group(polygon);
        polygon.setFill(colorPicker_SHAPES_FILL.getValue());
        polygon.setStroke(colorPicker_SHAPES_STROKE.getValue());
        polygon.setLayoutX(0);
        polygon.setLayoutY(0);
        MouseControlUtil.makeDraggable(pentagon);
        pentagon.setRotate(180.0);
        pane.getChildren().add(pentagon);

        pentagon.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                pane.getChildren().remove(pentagon);
            }
        });
    }

    public void onClearInputButton() {
        shapeSize_WIDTH.setText("");
        shapeSize_HEIGHT.setText("");
        shapeSize_RADIUS.setText("");
        shapeSize_STROKE.setText("");
    }

    public void onClearAllButton() {
        pane.getChildren().clear();
    }

    private void draw() {
        GraphicsContext context = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;

            if (erase.isSelected()){
                context.clearRect(x, y, size, size);
            }else {
                context.setFill(colorPicker_BRUSH.getValue());
                context.fillOval(x, y, size, size);
            }
        });
    }

    public void onSave() { //Added save dialog to save wherever you want
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        fileChooser.setTitle("Save Image");
        File file = fileChooser.showSaveDialog(null);
        try {
            Image snapshot = pane.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);        }catch (Exception e) {
            System.out.println("Failed to save: " + e);
        }
    }

    public void onOpen() {
       ImageView img_to_add = new ImageView();
        img_to_add.setX(0);
        img_to_add.setY(0);
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            img_to_add.setImage(image);
        }  catch (IOException ex) {
            ex.printStackTrace();
        }

        pane.getChildren().add(img_to_add);
    }

    public void onExit() {
        Platform.exit();
    }

    private void checkInput() {

        shapeSize_WIDTH.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    shapeSize_WIDTH.setText(oldValue);
                }
            }
        });

        shapeSize_HEIGHT.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    shapeSize_HEIGHT.setText(oldValue);
                }
            }
        });

        shapeSize_RADIUS.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    shapeSize_RADIUS.setText(oldValue);
                }
            }
        });

        shapeSize_STROKE.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    shapeSize_STROKE.setText(oldValue);
                }
            }
        });

        brushSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                    brushSize.setText(oldValue);
                }
            }
        });
    }
}