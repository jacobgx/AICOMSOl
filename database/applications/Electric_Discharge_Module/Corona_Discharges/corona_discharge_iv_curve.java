/*
 * corona_discharge_iv_curve.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:49 by COMSOL 6.3.0.290. */
public class corona_discharge_iv_curve {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Corona_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("edis", "ElectricDischarge", "geom1");

    model.study().create("std1");
    model.study("std1").create("esi", "ElectrostaticsInitialization");
    model.study("std1").feature("esi").set("solnum", "auto");
    model.study("std1").feature("esi").set("notsolnum", "auto");
    model.study("std1").feature("esi").set("outputmap", new String[]{});
    model.study("std1").feature("esi").set("ngenAUX", "1");
    model.study("std1").feature("esi").set("goalngenAUX", "1");
    model.study("std1").feature("esi").set("ngenAUX", "1");
    model.study("std1").feature("esi").set("goalngenAUX", "1");
    model.study("std1").feature("esi").setSolveFor("/physics/edis", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/edis", true);

    model.param().set("V0", "10[kV]");
    model.param().descr("V0", "Applied voltage");
    model.param().set("d", "10[cm]");
    model.param().descr("d", "Out-of-plane thickness");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.01);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 0.1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new int[]{1, 0});
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{3, 3});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0.5, 0});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{10, 10});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0.5, 0});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1", "c2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("edis").prop("PhysicalModel").set("dz", "d");
    model.component("comp1").physics("edis").feature("gas1").create("ece1", "Electrode", 1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").selection().set(9, 10, 11, 12);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("V0", "V0");
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").set("pBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("gas1").create("ece2", "Electrode", 1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").selection().set(13, 14, 15, 16);
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").set("eBnd", "NumberDensity");
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").set("nBnd", "NumberDensity");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChargeTransportGases", "ChargeTransportGases", "Charge transport in gases");
    model.component("comp1").material("mat1").label("Air [Kang et al. 2003]");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .label("Charge transport in gases");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_e", new String[]{"6060*(normE/1[V/cm])^0.75*1[cm/s]/normE", "0", "0", "0", "6060*(normE/1[V/cm])^0.75*1[cm/s]/normE", "0", "0", "0", "6060*(normE/1[V/cm])^0.75*1[cm/s]/normE"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_p", new String[]{"2.34*(normE/1[V/cm])^1*1[cm/s]/normE", "0", "0", "0", "2.34*(normE/1[V/cm])^1*1[cm/s]/normE", "0", "0", "0", "2.34*(normE/1[V/cm])^1*1[cm/s]/normE"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_n", new String[]{"2.7*(normE/1[V/cm])^1*1[cm/s]/normE", "0", "0", "0", "2.7*(normE/1[V/cm])^1*1[cm/s]/normE", "0", "0", "0", "2.7*(normE/1[V/cm])^1*1[cm/s]/normE"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("alpha", "3500*exp(-1.65e5/(normE/1[V/cm]))*1[cm^-1]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("eta", "15*exp(-2.5e4/(normE/1[V/cm]))*1[cm^-1]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("beta_ep", "1e-7[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("beta_pn", "1e-7[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("normE", "Electric field norm");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("N", "Neutral number density");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("EN", "Reduced electric field");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("electricfield");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("pressure");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(9, 10, 11, 12);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.04);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", 0.2);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(9, 10, 11, 12, 13, 14, 15, 16);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("esi").set("useparam", true);
    model.study("std1").feature("esi").setIndex("pname", "V0", 0);
    model.study("std1").feature("esi").setIndex("plistarr", "", 0);
    model.study("std1").feature("esi").setIndex("punit", "V", 0);
    model.study("std1").feature("esi").setIndex("pname", "V0", 0);
    model.study("std1").feature("esi").setIndex("plistarr", "", 0);
    model.study("std1").feature("esi").setIndex("punit", "V", 0);
    model.study("std1").feature("esi").setIndex("plistarr", 50, 0);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "V0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "V0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "50 100 200 500 range(1000,1000,10000)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 14, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "rho");
    model.result("pg1").feature("surf1").set("descr", "\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "edis.I0_0/d", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "uA/m", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Discharge Current", 0);
    model.result("pg2").run();

    model.title("\u7ebf\u5bf9\u7ebf\u7535\u6655\u653e\u7535\u7684\u7535\u6d41-\u7535\u538b\u7279\u6027");

    model
         .description("\u672c\u6a21\u578b\u5229\u7528\u8fde\u7eed\u6c42\u89e3\u5668\u9ad8\u6548\u8ba1\u7b97\u7531\u7ebf\u5bf9\u7ebf\u914d\u7f6e\u4ea7\u751f\u7684\u7535\u6655\u653e\u7535\u7684\u7535\u6d41-\u7535\u538b\u7279\u6027\uff0c\u5176\u4e2d\u63d0\u4f9b\u4e00\u4e2a\u7075\u6d3b\u7684\u6846\u67b6\uff0c\u4fbf\u4e8e\u8f7b\u677e\u8c03\u6574\u4ee5\u7814\u7a76\u5176\u4ed6\u653e\u7535\u914d\u7f6e\u548c\u7c7b\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("corona_discharge_iv_curve.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
