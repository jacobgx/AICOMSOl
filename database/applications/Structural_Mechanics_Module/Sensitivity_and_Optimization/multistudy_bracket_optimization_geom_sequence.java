/*
 * multistudy_bracket_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:15 by COMSOL 6.3.0.290. */
public class multistudy_bracket_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Sensitivity_and_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("thk", "4[mm]", "\u677f\u539a");
    model.param().set("lX", "65[mm]", "\u652f\u67b6\u957f\u5ea6");
    model.param().set("lZ", "70[mm]", "\u652f\u67b6\u9ad8\u5ea6");
    model.param().set("dCmp", "40[mm]", "\u6784\u4ef6\u76f4\u5f84");
    model.param().set("bDia", "8[mm]", "\u5b89\u88c5\u87ba\u6813\u7684\u76f4\u5f84");
    model.param().set("lY", "dCmp+4*bDia", "\u652f\u67b6\u5bbd\u5ea6");
    model.param().set("rIn", "2*thk", "\u5f2f\u5934\u5185\u534a\u5f84");
    model.param().set("rOut", "rIn+thk", "\u5f2f\u5934\u5916\u534a\u5f84");
    model.param().set("wInd", "12[mm]", "\u538b\u5165\u5bbd\u5ea6");
    model.param().set("dInd", "wInd/3", "\u538b\u5165\u6df1\u5ea6");
    model.param().set("rC", "4[mm]", "\u4e2d\u5fc3\u5b54\u534a\u5f84");
    model.param().set("zCo", "5[mm]", "\u5f2f\u5934\u5230\u4e2d\u5fc3\u5b54\u5e95\u90e8\u7684\u8ddd\u79bb");
    model.param().set("zC", "rOut+zCo+rC", "\u4e2d\u5fc3\u5b54\u7684 Z \u4f4d\u7f6e");
    model.param().set("rO", "5[mm]", "\u5916\u90e8\u5b54\u534a\u5f84");
    model.param().set("yOo", "8[mm]", "\u8fb9\u5230\u5916\u90e8\u5b54\u7684\u8ddd\u79bb");
    model.param().set("yO", "yOo+rO", "\u5916\u90e8\u5b54\u7684 Y \u4f4d\u7f6e");
    model.param().set("zOo", "20[mm]", "\u5f2f\u5934\u5230\u5916\u90e8\u5b54\u5e95\u90e8\u7684\u8ddd\u79bb");
    model.param().set("zO", "rOut+zOo+rO", "\u5916\u90e8\u5b54\u7684 Z \u4f4d\u7f6e");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").designBooleans(true);
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zy");
    model.component("comp1").geom("geom1").feature("wp1").set("quickx", "lX-rOut");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1")
         .set("coord", new String[]{"if((abs(s-0.5)<wInd/lY),dInd/2*(1+cos(pi*lY/if(wInd>4[mm],wInd,4[mm])*(s-0.5))),0)", ""});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pc1").setIndex("coord", "s*lY/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pc1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").selection("input").set("pc1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").set("displx", "thk");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"thk", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy2").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy2").set("disply", "lY/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("copy1", "copy2", "ls1", "pc1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("rev1").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev1").set("pos3", new String[]{"lX-rOut", "0", "rOut"});
    model.component("comp1").geom("geom1").feature("rev1").set("axis3", new int[]{0, -1, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"lX-rOut-2*thk", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "lY/2", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "thk", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("loft1", "Loft");
    model.component("comp1").geom("geom1").feature("loft1").set("unite", false);
    model.component("comp1").geom("geom1").feature("loft1").selection("startprofile").set("rev1", 1);
    model.component("comp1").geom("geom1").feature("loft1").selection("endprofile").set("blk1", 5);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("loft1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"lX-rOut", "0", "rOut"});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"thk", "lY/2", "lZ-rOut-2*thk"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"lX-thk", "0", "rOut+2*thk"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("cyl5", "Cylinder");
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").create("cyl6", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "dCmp/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "3*thk");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"lX-2*thk", "lY/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("pos", "lZ", 2);
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "bDia/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "3*thk");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"lX-2*thk", "bDia", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("pos", "lZ-bDia", 2);
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "bDia/2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "3*thk");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"lX-rOut-2*thk-bDia", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl3").setIndex("pos", "lY/4", 1);
    model.component("comp1").geom("geom1").feature("cyl3").setIndex("pos", "-thk", 2);
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "bDia/2");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "3*thk");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new String[]{"1.5*bDia", "lY/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl4").setIndex("pos", "-thk", 2);
    model.component("comp1").geom("geom1").feature("cyl5").set("r", "rO");
    model.component("comp1").geom("geom1").feature("cyl5").set("h", "3*thk");
    model.component("comp1").geom("geom1").feature("cyl5").set("pos", new String[]{"lX-2*thk", "yO", "zO"});
    model.component("comp1").geom("geom1").feature("cyl5").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl6").set("r", "rC");
    model.component("comp1").geom("geom1").feature("cyl6").set("h", "3*thk");
    model.component("comp1").geom("geom1").feature("cyl6").set("pos", new String[]{"lX-2*thk", "lY/2", "0"});
    model.component("comp1").geom("geom1").feature("cyl6").setIndex("pos", "zC", 2);
    model.component("comp1").geom("geom1").feature("cyl6").set("axistype", "x");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yx");
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "2*thk");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .set("x", "0 lY/2-bDia/2 lY/2-bDia/2 0 0 0");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .set("y", "0 0 0 lX-rOut-2*thk lX-rOut-2*thk 0");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "3*thk", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input")
         .set("blk1", "blk2", "loft1", "mir1", "rev1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2")
         .set("cyl1", "cyl2", "cyl3", "cyl4", "cyl5", "cyl6", "ext1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("pos", new String[]{"0", "lY/2", "0"});
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("multistudy_bracket_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
