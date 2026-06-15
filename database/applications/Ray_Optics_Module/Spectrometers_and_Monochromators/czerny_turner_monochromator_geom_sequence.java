/*
 * czerny_turner_monochromator_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:21 by COMSOL 6.3.0.290. */
public class czerny_turner_monochromator_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Spectrometers_and_Monochromators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");

    model.param().set("theta_g", "28.76[deg]");
    model.param().descr("theta_g", "\u5149\u6805\u89d2\u5ea6");
    model.param().set("theta_c", "11[deg]");
    model.param().descr("theta_c", "\u51c6\u76f4\u955c\u89d2\u5ea6");
    model.param().set("theta_i", "77[deg]");
    model.param().descr("theta_i", "\u6210\u50cf\u955c\u89d2\u5ea6");
    model.param().set("theta_d", "6.76[deg]");
    model.param().descr("theta_d", "\u63a2\u6d4b\u5668\u89d2\u5ea6");
    model.param().set("Qix", "20[mm]");
    model.param().descr("Qix", "x \u5750\u6807 Qi");
    model.param().set("Qiy", "34[mm]");
    model.param().descr("Qiy", "y \u5750\u6807 Qi");
    model.param().set("Qcx", "40[mm]");
    model.param().descr("Qcx", "x \u5750\u6807 Qc");
    model.param().set("Qcy", "40*tan(2*theta_c)");
    model.param().descr("Qcy", "y \u5750\u6807 Qc");
    model.param().set("Qdx", "22.08[mm]");
    model.param().descr("Qdx", "x \u5750\u6807 Qd");
    model.param().set("Qdy", "-24.12[mm]");
    model.param().descr("Qdy", "y \u5750\u6807 Qd");
    model.param().set("Ri", "130[mm]");
    model.param().descr("Ri", "\u6210\u50cf\u955c\u66f2\u7387\u534a\u5f84");
    model.param().set("Rc", "100[mm]");
    model.param().descr("Rc", "\u51c6\u76f4\u955c\u66f2\u7387\u534a\u5f84");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{3, 15});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-1.5*cos(theta_g)", "0"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("pos", "-1.5*sin(theta_g)", 1);
    model.component("comp1").geom("geom1").feature("r1").set("rot", "theta_g");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{3, 15});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"Qcx", "Qcy"});
    model.component("comp1").geom("geom1").feature("r2").set("rot", "theta_c");
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Rc");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"Qcx-Rc*cos(theta_c)", "0"});
    model.component("comp1").geom("geom1").feature("c1").setIndex("pos", "Qcy-Rc*sin(theta_c)", 1);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{3, 30});
    model.component("comp1").geom("geom1").feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"Qix", "Qiy"});
    model.component("comp1").geom("geom1").feature("r3").set("rot", "theta_i");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "Ri");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"Qix-Ri*cos(theta_i)", "0"});
    model.component("comp1").geom("geom1").feature("c2").setIndex("pos", "Qiy-Ri*sin(theta_i)", 1);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("c2");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new int[]{30, 3});
    model.component("comp1").geom("geom1").feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"Qdx+1.5*sin(theta_d)", "0"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("pos", "Qdy-1.5*cos(theta_d)", 1);
    model.component("comp1").geom("geom1").feature("r4").set("rot", "theta_d");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("czerny_turner_monochromator_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
