/*
 * homogenization_llmatlab.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:43 by COMSOL 6.3.0.290. */
public class homogenization_llmatlab {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_MATLAB\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

    model.param().set("L", "50[cm]");
    model.param().descr("L", "Geometry length");
    model.param().set("D", "2e-5[m^2/s]");
    model.param().descr("D", "Diffusion coefficient");
    model.param().set("c0", "1[mol/m^3]");
    model.param().descr("c0", "Applied concentration");
    model.param().set("k", "5e-11[1/s]");
    model.param().descr("k", "Reaction rate");
    model.param().set("t0", "0");
    model.param().descr("t0", "Initial time");
    model.param().set("dt", "3[min]");
    model.param().descr("dt", "Output time step");
    model.param().set("T", "30[min]");
    model.param().descr("T", "Period of homogenization");
    model.param().set("tf", "t0+T");
    model.param().descr("tf", "Final computational time");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L", "L", "L/2"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("point1", new String[]{"4/5*L", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("point2", new String[]{"0", "4/5*L"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tds").create("reac1", "Reactions", 3);
    model.component("comp1").physics("tds").feature("reac1").selection().set(1);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_c", "k*c", 0);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 2);
    model.component("comp1").physics("tds").feature("conc1").selection().set(3);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c0", 0);

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(t0,dt,tf)");

    model.title("\u5316\u5b66\u53cd\u5e94\u5668\u5747\u5316\u5de5\u827a");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u7a7a\u95f4\u76f8\u5173\u7684\u5316\u5b66\u53cd\u5e94\u5668\u6a21\u578b\u6a21\u62df\u5468\u671f\u6027\u5747\u5316\u5de5\u827a\u3002\u8fd9\u79cd\u5de5\u827a\u53ef\u4ee5\u6309\u8bbe\u7f6e\u7684\u65f6\u95f4\u95f4\u9694\u6d88\u9664\u53cd\u5e94\u5668\u4e2d\u7684\u6d53\u5ea6\u68af\u5ea6\u3002");

    model.label("homogenization_llmatlab.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
