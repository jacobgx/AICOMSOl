/*
 * flow_meter_piezoelectric_transducers_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class flow_meter_piezoelectric_transducers_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Ultrasound");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D", "5[mm]", "\u4e3b\u7ba1\u9053\u76f4\u5f84");
    model.param().set("L", "4*D", "\u4e3b\u7ba1\u9053\u957f\u5ea6");
    model.param().set("alpha", "45[deg]", "\u6362\u80fd\u5668\u7ba1\u9053\u503e\u89d2");
    model.param().set("D_transducer", "2[mm]", "\u6362\u80fd\u5668\u76f4\u5f84");
    model.param()
         .set("L_transducer", "D/cos(alpha)+3/2*D_transducer/cos(alpha)*tan(alpha)", "\u6362\u80fd\u5668\u7ba1\u9053\u957f\u5ea6");
    model.param().set("L2", "D/sin(alpha)", "\u4e3b\u6d41\u4e2d\u7684\u6362\u80fd\u5668\u7ba1\u9053\u957f\u5ea6");
    model.param()
         .set("L1", "0.5*(L_transducer-L2)", "\u652f\u6d41\u4e2d\u7684\u6362\u80fd\u5668\u7ba1\u9053\u957f\u5ea6");
    model.param().set("L_matching", "cp_match/f0/4", "\u5339\u914d\u5c42\u539a\u5ea6");
    model.param().set("L_piezo", "cp_pzt/f0/2", "\u538b\u7535\u6362\u80fd\u5668\u539a\u5ea6");
    model.param()
         .set("nLx", "cos(alpha)", "\u6362\u80fd\u5668\u7ba1\u9053\u65b9\u5411\u7684\u5355\u4f4d\u77e2\u91cf\uff08x \u5206\u91cf\uff09");
    model.param()
         .set("nLy", "0", "\u6362\u80fd\u5668\u7ba1\u9053\u65b9\u5411\u7684\u5355\u4f4d\u77e2\u91cf\uff08y \u5206\u91cf\uff09");
    model.param()
         .set("nLz", "sin(alpha)", "\u6362\u80fd\u5668\u7ba1\u9053\u65b9\u5411\u7684\u5355\u4f4d\u77e2\u91cf\uff08z \u5206\u91cf\uff09");
    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("Uin", "10[m/s]", "\u5165\u53e3\u5904\u80cc\u666f\u5e73\u5747\u6d41\u7684\u5e73\u5747\u901f\u5ea6");
    model.param("par2").set("V0", "50[V]", "\u9a71\u52a8\u7535\u538b");
    model.param("par2").set("f0", "2.5[MHz]", "\u8f7d\u6ce2\u4fe1\u53f7\u4e2d\u5fc3\u9891\u7387");
    model.param("par2").set("T0", "1/f0", "\u8f7d\u6ce2\u4fe1\u53f7\u5468\u671f");
    model.param("par2").set("lam0", "cp_water/f0", "\u6c34\u4e2d\u7684\u4fe1\u53f7\u6ce2\u957f");
    model.param("par2").set("cp_water", "1481[m/s]", "\u58f0\u901f\uff0c\u6c34");
    model.param("par2").set("cp_pzt", "4620[m/s]", "\u538b\u529b\u6ce2\u901f\uff0cPZT");
    model.param("par2").set("cs_pzt", "1750[m/s]", "\u526a\u5207\u6ce2\u901f\uff0cPZT");
    model.param("par2").set("cp_match", "3400[m/s]", "\u538b\u529b\u6ce2\u901f\uff0c\u5339\u914d\u6750\u6599");
    model.param("par2").set("cs_match", "1920[m/s]", "\u526a\u5207\u6ce2\u901f\uff0c\u5339\u914d\u6750\u6599");
    model.param("par2").set("rho_match", "2280[kg/m^3]", "\u5bc6\u5ea6\uff0c\u5339\u914d\u6750\u6599");
    model.param("par2").set("cp_damp", "1500[m/s]", "\u538b\u529b\u6ce2\u901f\uff0c\u5438\u97f3\u6750\u6599");
    model.param("par2").set("cs_damp", "775[m/s]", "\u526a\u5207\u6ce2\u901f\uff0c\u5438\u97f3\u6750\u6599");
    model.param("par2").set("rho_damp", "6580[kg/m^3]", "\u5bc6\u5ea6\uff0c\u5438\u97f3\u6750\u6599");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "D/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "0.5*D", 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("layertop", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "D_transducer/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L_transducer");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"L/2", "0", "-L_transducer/2"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "D_transducer/2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "L_matching");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"L/2", "0", "L_transducer/2"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "D_transducer/4");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "L_piezo");
    model.component("comp1").geom("geom1").feature("cyl4")
         .set("pos", new String[]{"L/2", "0", "L_transducer/2+L_matching"});
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("cyl5", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", "D_transducer/2");
    model.component("comp1").geom("geom1").feature("cyl5").set("h", "2*L_piezo");
    model.component("comp1").geom("geom1").feature("cyl5")
         .set("pos", new String[]{"L/2", "0", "L_transducer/2+L_matching"});
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl5");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl4");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("cyl3", "cyl4", "dif1");
    model.component("comp1").geom("geom1").feature("copy1").set("displz", "-L_transducer");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("copy1");
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"0", "0", "-L_transducer/2"});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input")
         .set("cyl2", "cyl3", "cyl4", "dif1", "mir1");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "alpha");
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"L/2", "0", "0"});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("cyl1", "rot1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set(new String[]{"par1(1)", "par1(2)", "par1(3)", "par1(4)", "par1(5)", "par1(6)", "par1(7)", "par1(8)"}, new int[][]{{1, 3, 5}, {1}, {1}, {1}, {1}, {1}, {1}, {1}});
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("del1(1)", "del1(2)");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("uni1", 13, 14, 16, 19);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 19, 20, 23, 27);
    model.component("comp1").geom("geom1").run("ige1");

    model.title(null);

    model.description("");

    model.label("flow_meter_piezoelectric_transducers_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
