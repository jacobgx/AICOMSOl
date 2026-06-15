/*
 * small_concert_hall_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class small_concert_hall_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "small_concert_hall.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("imp1", 40);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("del1", 39);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("ext1", 41);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u7a97\u6237");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("del2", 63, 64, 65);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u5ea7\u6905");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("del2", 39, 40, 41, 42, 59);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u6269\u97f3\u5668");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .set("del2", 13, 15, 29, 30, 43, 44, 51, 52);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u5730\u677f");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("del2", 3, 8, 12, 14, 18, 21);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .set("del2", 16, 19, 20, 23, 31, 32);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u58c1");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection")
         .set("del2", 1, 2, 4, 5, 6, 7, 9, 10, 11, 17, 22, 24, 25, 26, 27, 28, 34, 35, 36, 37, 45, 46, 47, 48, 49, 50, 53, 54, 55, 56, 57, 58, 60, 61, 62);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u5438\u58f0\u5668");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("del2", 33, 38);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("small_concert_hall_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
