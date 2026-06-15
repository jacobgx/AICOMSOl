/*
 * one_family_house_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class one_family_house_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("x_s", "-2[m]", "\u6e90 x \u5750\u6807");
    model.param().set("y_s", "4[m]", "\u6e90 y \u5750\u6807");
    model.param().set("z_s", "0.4[m]", "\u6e90 z \u5750\u6807");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "one_family_house.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "x_s", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "y_s", 1);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "z_s", 2);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{9, 5, 5});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-4.5, 4.695, 0.3});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("imp1");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").feature("dif1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("dif1", 6);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("dif1", 7);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("dif1", 27);
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex2").set("dif1", 29);
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex1").set("dif1", 54);
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex2").set("dif1", 55);
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u7a97\u6237");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .set("imp1", 1, 2, 6, 37, 42, 48, 104, 105, 140, 156, 208, 251, 252);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u6728\u5730\u677f");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .set("imp1", 8, 13, 17, 26, 56, 95, 118, 135, 144, 191);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u697c\u68af");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .set("imp1", 82, 128, 129, 130, 153, 154, 155, 170, 171, 177, 179, 180, 200, 201, 202, 203, 213, 214, 215, 216, 221, 222, 223, 224, 226, 227, 228, 229, 230, 231, 232, 233, 234);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u5929\u82b1\u677f");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .set("imp1", 14, 18, 22, 27, 39, 40, 57, 83, 96, 119, 136, 145, 186, 187, 188, 192);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u95e8 1");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("imp1", 107, 109);
    model.component("comp1").geom("geom1").nodeGroup().create("grp1");
    model.component("comp1").geom("geom1").nodeGroup("grp1").placeAfter("sel4");
    model.component("comp1").geom("geom1").nodeGroup("grp1").add("sel5");
    model.component("comp1").geom("geom1").nodeGroup("grp1").label("\u623f\u95f4\u5206\u9694");
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u95e8 2");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("imp1", 79, 85);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u95e8 3");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("imp1", 78, 80);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("\u95e8 4");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("imp1", 162, 164);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("\u95e8 5");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("imp1", 141, 142);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").create("sel10", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel10").label("\u95e8 6");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("imp1", 31, 33);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").create("sel11", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel11").label("\u95e8 7");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").set("imp1", 185, 189);
    model.component("comp1").geom("geom1").run("sel11");
    model.component("comp1").geom("geom1").create("sel12", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel12").label("\u95e8 8");
    model.component("comp1").geom("geom1").feature("sel12").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel12").selection("selection").set("imp1", 60, 62);
    model.component("comp1").geom("geom1").run("sel12");
    model.component("comp1").geom("geom1").create("sel13", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel13").label("\u95e8 9");
    model.component("comp1").geom("geom1").feature("sel13").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel13").selection("selection").set("imp1", 127);
    model.component("comp1").geom("geom1").run("sel13");
    model.component("comp1").geom("geom1").create("sel14", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel14").label("\u5899\u58c1\u5206\u5272");
    model.component("comp1").geom("geom1").feature("sel14").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel14").selection("selection").set("imp1", 76, 93);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("one_family_house_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
