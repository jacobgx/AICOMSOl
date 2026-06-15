/*
 * buckley_leverett_model.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:14 by COMSOL 6.3.0.290. */
public class buckley_leverett_model {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("phtr", "PhaseTransportPorous", "geom1");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", "1");

    model.component("comp1").multiphysics().create("mfpm1", "MultiphaseFlowInPorousMedia", 1);
    model.component("comp1").multiphysics("mfpm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfpm1").set("darcyc_physics", "dl");
    model.component("comp1").multiphysics("mfpm1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfpm1", true);

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").setIndex("pieces", 0.7071, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "d(x^2/(x^2+(1-x)^2),x)", 0, 2);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("rhoint_s1_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("mu_s1_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("rhoint_s2_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("mu_s2_mat", "userdef");
    model.component("comp1").physics("phtr").create("sa1", "VolumeFraction", 0);
    model.component("comp1").physics("phtr").feature("sa1").selection().set(1);
    model.component("comp1").physics("phtr").feature("sa1").setIndex("phases", true, 1);
    model.component("comp1").physics("phtr").feature("sa1").setIndex("s0", 1, 1);
    model.component("comp1").physics("phtr").create("of1", "Outflow", 0);
    model.component("comp1").physics("phtr").feature("of1").selection().set(2);
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", 2);
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", 0.5);
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"1e-9[m^2]", "0", "0", "0", "1e-9[m^2]", "0", "0", "0", "1e-9[m^2]"});
    model.component("comp1").physics("dl").create("inl1", "Inlet", 0);
    model.component("comp1").physics("dl").feature("inl1").selection().set(1);
    model.component("comp1").physics("dl").feature("inl1").set("U0in", 0.001);
    model.component("comp1").physics("dl").create("pr1", "Pressure", 0);
    model.component("comp1").physics("dl").feature("pr1").selection().set(2);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 400);

    model.study("std1").feature("time").set("tlist", "range(0,20,300)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t1").set("estrat", "exclude");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u4f53\u79ef\u5206\u6570 (phtr)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").label("\u7ebf\u7ed3\u679c\u56fe");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("expr", "s2");
    model.result("pg1").feature("lngr1").set("smooth", "internal");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u538b\u529b (dl)");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").label("\u7ebf\u7ed3\u679c\u56fe");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("expr", "p");
    model.result("pg2").feature("lngr1").set("smooth", "internal");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u76f8 2 \u7684\u4f53\u79ef\u5206\u6570 (1)");
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr2").set("linewidth", "preference");
    model.result("pg1").feature("lngr2").selection().set(1);
    model.result("pg1").feature("lngr2").set("expr", "x");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "pw1(x)*(0.001*t)/0.5");
    model.result("pg1").feature("lngr2").set("linestyle", "dotted");
    model.result("pg1").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg1").run();

    model.title("\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u4e24\u76f8\u6d41\uff1aBuckley-Leverett \u6a21\u578b");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4e3a\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u4e24\u76f8\u6d41\u8fdb\u884c\u5efa\u6a21\u3002\u5728\u8fd9\u4e2a\u4e00\u7ef4\u6a21\u578b\u4e2d\uff0c\u6d41\u4f53\u6d41\u52a8\u53ef\u4ee5\u7528 Buckley-Leverett \u65b9\u7a0b\u6765\u63cf\u8ff0\uff0c\u56e0\u800c\u53ef\u4ee5\u83b7\u5f97\u89e3\u6790\u89e3\uff0c\u5e76\u4f5c\u4e3a\u57fa\u51c6\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("buckley_leverett_model.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
