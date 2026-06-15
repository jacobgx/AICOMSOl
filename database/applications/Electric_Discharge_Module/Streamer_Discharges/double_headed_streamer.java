/*
 * double_headed_streamer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:51 by COMSOL 6.3.0.290. */
public class double_headed_streamer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Streamer_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

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
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/edis", true);

    model.param().set("V0", "52[kV]");
    model.param().descr("V0", "Applied voltage");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1")
         .set("N0", "(1e8+1e14*exp(-((z/(1[cm])-0.5)/0.027)^2-(r/(1[cm])/0.021)^2))[cm^-3]");
    model.component("comp1").variable("var1").descr("N0", "Initial number density");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.06, 0);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("edis").feature("gas1").set("ChargeCarriers", "e_p");
    model.component("comp1").physics("edis").feature("gas1").set("mu_p_mat", "userdef");
    model.component("comp1").physics("edis").feature("gas1").set("mu_p", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp1").physics("edis").feature("gas1").set("DiffusionCoefficientMethod", "UserDefined");
    model.component("comp1").physics("edis").feature("gas1")
         .set("D_e", new String[]{"1800[cm^2/s]", "0", "0", "0", "0", "0", "0", "0", "2190[cm^2/s]"});
    model.component("comp1").physics("edis").feature("gas1").set("D_p", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
    model.component("comp1").physics("edis").feature("gas1").set("beta_ep_mat", "userdef");
    model.component("comp1").physics("edis").feature("gas1").create("ece1", "Electrode", 1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece1").selection().set(2, 5);
    model.component("comp1").physics("edis").feature("gas1").create("ece2", "Electrode", 1);
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").selection().set(3, 6);
    model.component("comp1").physics("edis").feature("gas1").feature("ece2").set("V0", "V0");
    model.component("comp1").physics("edis").feature("gas1").feature("init1").setIndex("n0_e", "N0", 0);
    model.component("comp1").physics("edis").feature("gas1").feature("init1").setIndex("n0_p", "N0", 0);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").info().create("matinfo1");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChargeTransportGases", "ChargeTransportGases", "Charge transport in gases");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").label("N2 - Nitrogen");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").set("phase", "gas");
    model.component("comp1").material("mat1").set("info", new String[][]{{"matinfo1", "", ""}});
    model.component("comp1").material("mat1").info("matinfo1").label("Information 1");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-15[S/m]", "0", "0", "0", "1e-15[S/m]", "0", "0", "0", "1e-15[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .label("Charge transport in gases");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("funcname", "alpha");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("expr", "5.7*P*exp(-260*P/E)");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("args", new String[]{"P", "E"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("fununit", "1/cm");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("argunit", new String[]{"Torr", "V/cm"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("plotaxis", new String[]{"on", "on"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("plotfixedvalue", new String[]{"0", "0"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").func("an1")
         .set("plotargs", new String[][]{{"P", "0", "1"}, {"E", "0", "1"}});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_e", new String[]{"2.9e5/(pA/1[Torr])*1[cm^2/V/s]", "0", "0", "0", "2.9e5/(pA/1[Torr])*1[cm^2/V/s]", "0", "0", "0", "2.9e5/(pA/1[Torr])*1[cm^2/V/s]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("mu_e", "Reference: Dhali, S. K., and P. F. Williams. \"Two\u2010dimensional studies of streamers in gases.\" Journal of applied physics 62, no. 12 (1987): 4696-4707.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .set("mu_p", new String[]{"2.6e3/(pA/1[Torr])*1[cm^2/V/s]", "0", "0", "0", "2.6e3/(pA/1[Torr])*1[cm^2/V/s]", "0", "0", "0", "2.6e3/(pA/1[Torr])*1[cm^2/V/s]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("mu_p", "Reference: Dhali, S. K., and P. F. Williams. \"Two\u2010dimensional studies of streamers in gases.\" Journal of applied physics 62, no. 12 (1987): 4696-4707.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("alpha", "alpha(pA,normE)");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .setPropertyInfo("alpha", "Reference: Dhali, S. K., and P. F. Williams. \"Two\u2010dimensional studies of streamers in gases.\" Journal of applied physics 62, no. 12 (1987): 4696-4707.");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").set("beta_ep", "2e-7[cm^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("normE", "\u7535\u573a\u6a21");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("N", "\u4e2d\u6027\u6570\u5bc6\u5ea6");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases")
         .descr("EN", "\u7ea6\u5316\u7535\u573a");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("electricfield");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportGases").addInput("pressure");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 800);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "ns");
    model.study("std1").feature("time").set("tlist", "range(0,0.5,2.5)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", 0.01);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "edis.Ez");
    model.result("pg1").feature("lngr1").set("unit", "kV/cm");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "edis.n_e");
    model.result("pg2").feature("con1").set("unit", "1/cm^3");
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("levels", "range(1.0e13,1.0e13,1.5e14)");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").set("view", "view1");

    model.component("comp1").view("view1").axis().set("xmin", 0);
    model.component("comp1").view("view1").axis().set("xmax", 0.06);
    model.component("comp1").view("view1").axis().set("ymin", 0);
    model.component("comp1").view("view1").axis().set("ymax", 1);
    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.result("pg2").run();
    model.result("pg2").feature("con1").create("trn1", "Transformation");
    model.result("pg2").run();
    model.result("pg2").feature("con1").feature("trn1").set("enablerot", true);
    model.result("pg2").feature("con1").feature("trn1").set("rotangle", "-90");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u5e73\u884c\u677f\u7535\u6781\u4e2d\u7684\u53cc\u5934\u6d41\u6ce8");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u5728\u5927\u6c14\u538b\u4e0b\u6c2e\u6c14\u4e2d\u53cc\u5934\u6d41\u6ce8\u7684\u4f20\u64ad\u60c5\u51b5\u3002\u6700\u521d\uff0c\u5728\u4e24\u4e2a\u5e73\u884c\u7535\u6781\u4e4b\u95f4\u5f15\u5165\u7535\u5b50-\u6b63\u79bb\u5b50\u56e2\u7c07\uff0c\u4f7f\u6c14\u4f53\u5904\u4e8e 52\u00a0kV/cm \u7684\u5f3a\u80cc\u666f\u7535\u573a\u4e2d\u3002\u968f\u540e\uff0c\u8d1f\u6d41\u6ce8\u548c\u6b63\u6d41\u6ce8\u5747\u5411\u5bf9\u5e94\u7535\u6781\u53d1\u5c55\u3002\u7535\u5b50\u5bc6\u5ea6\u3001\u7535\u573a\u5f3a\u5ea6\u548c\u6d41\u6ce8\u4f20\u64ad\u901f\u5ea6\u4e0e\u6587\u732e\u4e2d\u7684\u7ed3\u679c\u975e\u5e38\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("double_headed_streamer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
