/*
 * spacecraft_thermal_analysis_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:24 by COMSOL 6.3.0.290. */
public class spacecraft_thermal_analysis_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Orbital_Thermal_Loads");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"10[cm]", "10[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "10[cm]", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"9[cm]", "9[cm]", "9[cm]"});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"9[cm]", "9[cm]", "5[mm]"});
    model.component("comp1").geom("geom1").feature("blk3").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "0", "4.75[cm]"});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("blk3");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("blk3", "mir1");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 90);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("rot2").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot2").set("rot", 90);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("blk3", "mir1", "rot1", "rot2");
    model.component("comp1").geom("geom1").feature("dif2").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"9[cm]", "9[cm]", "5[mm]"});
    model.component("comp1").geom("geom1").feature("blk4").set("base", "center");
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"3[cm]", "6[cm]", "2[cm]"});
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new String[]{"-3[cm]", "-3.5[cm]", "0"});
    model.component("comp1").geom("geom1").feature("blk5").setIndex("pos", "-2.25[cm]", 2);
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("blk6", "Block");
    model.component("comp1").geom("geom1").feature("blk6").set("size", new String[]{"1.5[cm]", "2[cm]", "1"});
    model.component("comp1").geom("geom1").feature("blk6").setIndex("size", "0.75[cm]", 2);
    model.component("comp1").geom("geom1").feature("blk6").set("pos", new String[]{"2.5[cm]", "-4[cm]", "0"});
    model.component("comp1").geom("geom1").feature("blk6").setIndex("pos", "0.25[cm]", 2);
    model.component("comp1").geom("geom1").run("blk6");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "2[cm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "6.5[cm]");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "2.5[mm]"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("blk3");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif3").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "1.8[cm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "6.5[cm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "2[cm]"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("dif4", "Difference");
    model.component("comp1").geom("geom1").feature("dif4").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif4").selection("input2").set("cyl2");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("spacecraft_thermal_analysis_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
