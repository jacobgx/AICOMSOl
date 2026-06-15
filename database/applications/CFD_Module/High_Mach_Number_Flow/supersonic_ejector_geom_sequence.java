/*
 * supersonic_ejector_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class supersonic_ejector_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\High_Mach_Number_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("d_throat", "8[mm]");
    model.param().descr("d_throat", "\u5589\u90e8\u76f4\u5f84");
    model.param().set("d_secondary", "160[mm]");
    model.param().descr("d_secondary", "\u4e8c\u6b21\u6d41\u5165\u53e3\u76f4\u5f84");
    model.param().set("d_primary", "16[mm]");
    model.param().descr("d_primary", "\u4e3b\u6d41\u5165\u53e3\u76f4\u5f84");
    model.param().set("d_divergent", "12[mm]");
    model.param().descr("d_divergent", "\u55b7\u5634\u6269\u6563\u6bb5\u76f4\u5f84");
    model.param().set("d_diffuser", "51[mm]");
    model.param().descr("d_diffuser", "\u6269\u6563\u5668\u76f4\u5f84");
    model.param().set("d_mixing", "24[mm]");
    model.param().descr("d_mixing", "\u6df7\u5408\u5ba4\u76f4\u5f84");
    model.param().set("L_mixing", "240[mm]");
    model.param().descr("L_mixing", "\u6df7\u5408\u5ba4\u957f\u5ea6");
    model.param().set("L_diffuser", "70[mm]");
    model.param().descr("L_diffuser", "\u6269\u6563\u5668\u957f\u5ea6");
    model.param().set("NXP", "15[mm]");
    model.param()
         .descr("NXP", "\u55b7\u5634\u51fa\u53e3\u548c\u6df7\u5408\u5ba4\u5165\u53e3\u4e4b\u95f4\u7684\u8ddd\u79bb");
    model.param().set("L_convergent", "7[mm]");
    model.param().descr("L_convergent", "\u55b7\u5634\u6536\u7f29\u6bb5\u957f\u5ea6");
    model.param().set("L_secondary", "90[mm]");
    model.param().descr("L_secondary", "\u4e8c\u6b21\u6d41\u55b7\u5634\u957f\u5ea6");
    model.param().set("L_divergent", "23[mm]");
    model.param().descr("L_divergent", "\u55b7\u5634\u6269\u6563\u6bb5\u957f\u5ea6");
    model.param().set("L_in", "15[mm]");
    model.param().descr("L_in", "\u5165\u53e3\u957f\u5ea6");
    model.param().set("L_out", "L_in");
    model.param().descr("L_out", "\u51fa\u53e3\u957f\u5ea6");
    model.param().set("thickness", "0.8[mm]");
    model.param().descr("thickness", "\u55b7\u5634\u58c1\u6700\u5927\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").label("\u58c1");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("x", "d_primary/2, d_primary/2, d_primary/2, d_throat/2, d_throat/2, d_divergent/2, d_divergent/2, d_throat/2+thickness, d_throat/2+thickness, d_primary/2+thickness, d_primary/2+thickness, d_primary/2+thickness, d_primary/2+thickness, d_primary/2");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("y", "-L_in-L_secondary, -NXP-L_divergent-L_convergent, -NXP-L_divergent-L_convergent, -L_divergent-NXP, -L_divergent-NXP, -NXP, -NXP, -NXP-L_divergent, -NXP-L_divergent, -NXP-L_divergent-L_convergent, -NXP-L_divergent-L_convergent, -L_in-L_secondary, -L_in-L_secondary, -L_in-L_secondary");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").label("\u55b7\u5c04\u5668");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol2")
         .set("x", "0 0 0 d_diffuser/2 d_diffuser/2 d_diffuser/2 d_diffuser/2 d_mixing/2 d_mixing/2 d_mixing/2 d_mixing/2 d_secondary/2 d_secondary/2 d_secondary/2 d_secondary/2 0");
    model.component("comp1").geom("geom1").feature("pol2")
         .set("y", "-L_in-L_secondary L_mixing+L_diffuser+L_out L_mixing+L_diffuser+L_out L_mixing+L_diffuser+L_out L_mixing+L_diffuser+L_out L_mixing+L_diffuser L_mixing+L_diffuser L_mixing L_mixing 0 0 -L_secondary -L_secondary -L_secondary-L_in -L_secondary-L_in -L_secondary-L_in");
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("pol2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("pol1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d_primary/2", "1"});
    model.component("comp1").geom("geom1").feature("r1")
         .setIndex("size", "L_secondary-NXP-L_divergent-L_convergent", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-L_secondary-L_in"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("r1");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1")
         .set("coord1", new String[]{"0", "-NXP-L_divergent-L_convergent"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"d_primary/2", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", "-NXP-L_divergent-L_convergent", 1);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"d_divergent/2", "0"});
    model.component("comp1").geom("geom1").feature("ls2").setIndex("coord1", "-NXP", 1);
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"d_divergent/2", "0"});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"d_mixing/2", "0"});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4, 6, 12, 13);
    model.component("comp1").geom("geom1").run("mce1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u591a\u4e2a\u58c1");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .set("mce1", 4, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u4e3b\u6d41\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("mce1", 2);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u4e8c\u6b21\u6d41\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("mce1", 10);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("mce1", 3);
    model.component("comp1").geom("geom1").run("sel4");

    model.title(null);

    model.description("");

    model.label("supersonic_ejector_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
