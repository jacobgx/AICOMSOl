/*
 * charge_transport_in_polyethylene.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:51 by COMSOL 6.3.0.290. */
public class charge_transport_in_polyethylene {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Solid_Dielectrics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

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

    model.param().set("d", "150[um]");
    model.param().descr("d", "Thickness");
    model.param().set("E", "20[kV/mm]");
    model.param().descr("E", "Initial electric field");
    model.param().set("V0", "d*E");
    model.param().descr("V0", "Applied voltage");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "d", 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("edis").prop("PhysicalModel").set("Gas", false);
    model.component("comp1").physics("edis").prop("PhysicalModel").set("Solid", true);
    model.component("comp1").physics("edis").prop("Stabilization").set("IsotropicDiffusion", true);
    model.component("comp1").physics("edis").prop("Stabilization").set("delid", 0.25);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChargeTransportSolids", "ChargeTransportSolids", "Charge transport in solids");
    model.component("comp1").material("mat1").label("Polyethylene");
    model.component("comp1").material("mat1").set("phase", "solid");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"2.3", "0", "0", "0", "2.3", "0", "0", "0", "2.3"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-16[S/m]", "0", "0", "0", "1e-16[S/m]", "0", "0", "0", "1e-16[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids")
         .label("Charge transport in solids");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids")
         .set("mu_be", new String[]{"1e-14[m^2/V/s]", "0", "0", "0", "1e-14[m^2/V/s]", "0", "0", "0", "1e-14[m^2/V/s]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids")
         .set("mu_bh", new String[]{"1e-14[m^2/V/s]", "0", "0", "0", "1e-14[m^2/V/s]", "0", "0", "0", "1e-14[m^2/V/s]"});
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("phi_st_e", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("phi_st_h", "0[V]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("nu_te", "6e-12[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("nu_th", "6e-12[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("phi_te", "0.96[V]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("phi_th", "0.99[V]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("B_e", "7e-3[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("B_h", "7e-3[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("n_0_te", "6.24e20[1/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("n_0_th", "6.24e20[1/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("C_0", "6.4e-22[m^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("C_1", "6.4e-22[m^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("C_2", "6.4e-22[m^3/s]");
    model.component("comp1").material("mat1").propertyGroup("ChargeTransportSolids").set("C_3", "0[m^3/s]");

    model.component("comp1").physics("edis").feature("solid1").create("ece1", "Electrode", 0);
    model.component("comp1").physics("edis").feature("solid1").feature("ece1").selection().set(1);
    model.component("comp1").physics("edis").feature("solid1").create("ece2", "Electrode", 0);
    model.component("comp1").physics("edis").feature("solid1").feature("ece2").selection().set(2);
    model.component("comp1").physics("edis").feature("solid1").feature("ece2").set("V0", "V0");
    model.component("comp1").physics("edis").feature("solid1").feature("init1")
         .setIndex("n0_e", "0.5[C/m^3]/e_const", 0);
    model.component("comp1").physics("edis").feature("solid1").feature("init1")
         .setIndex("n0_h", "0.5[C/m^3]/e_const", 0);
    model.component("comp1").physics("edis").feature("solid1").feature("init1")
         .setIndex("n0_te", "0.1[C/m^3]/e_const", 0);
    model.component("comp1").physics("edis").feature("solid1").feature("init1")
         .setIndex("n0_th", "0.1[C/m^3]/e_const", 0);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 1000);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "d", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "d", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "E", 0);
    model.study("std1").feature("param").setIndex("plistarr", "20 50 80", 0);
    model.study("std1").feature("param").setIndex("punit", "kV/mm", 0);
    model.study("std1").feature("time").set("tlist", "10^{range(log10(1),1/10,log10(1e4))}");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevelinput", "last", 1);
    model.result("pg1").setIndex("looplevelinput", "interp", 0);
    model.result("pg1").setIndex("interp", "1 1e2 1e4", 0);
    model.result("pg1").set("legendpos", "lowermiddle");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "edis.n_e");
    model.result("pg1").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg1").feature("lngr1").set("linemarker", "cycle");
    model.result("pg1").feature("lngr1").set("markerpos", "interp");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("autodescr", true);
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("expr", "edis.n_h");
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linemarker", "cyclereset");
    model.result("pg1").set("ylog", true);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "uppermiddle");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevelinput", "interp", 0);
    model.result("pg2").setIndex("interp", "1 1e2 1e4", 0);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", "edis.Ex");
    model.result("pg2").feature("lngr1").set("unit", "kV/mm");
    model.result("pg2").feature("lngr1").set("linemarker", "cycle");
    model.result("pg2").feature("lngr1").set("markerpos", "interp");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").setIndex("interp", "10^{range(log10(100),1/10,log10(1e4))}", 0);
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("ylog", true);
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "edis.I0_1", 0);
    model.result("pg3").feature("glob1").set("linemarker", "cycle");
    model.result("pg3").run();
    model.result().dataset().create("par1", "Parametric1D");
    model.result().dataset("par1").set("data", "dset3");
    model.result().dataset("par1").setIndex("looplevelinput", "last", 1);
    model.result().dataset("par1").set("leveltrans", "expression");
    model.result().dataset("par1").set("transexpr", "log10(level)");
    model.result().dataset().create("tran1", "Transformation2D");
    model.result().dataset("tran1").set("transtype", "general");
    model.result().dataset("tran1").setIndex("transmatrix", 0, 0, 0);
    model.result().dataset("tran1").setIndex("transmatrix", 1, 0, 1);
    model.result().dataset("tran1").setIndex("transmatrix", 1, 1, 0);
    model.result().dataset("tran1").setIndex("transmatrix", 0, 1, 1);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").set("data", "tran1");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("edges", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", -10);
    model.result("pg4").feature("surf1").set("rangecolormax", 10);
    model.result("pg4").feature("surf1").set("colortable", "Ranitomeya");
    model.result("pg4").run();
    model.result("pg4").run();

    model.title("\u805a\u4e59\u70ef\u4e2d\u7684\u7535\u8377\u4f20\u8f93\u52a8\u529b\u5b66");

    model
         .description("\u672c\u7814\u7a76\u63a2\u8ba8\u805a\u4e59\u70ef\uff08\u4e00\u79cd\u5e38\u89c1\u7684\u56fa\u4f53\u7535\u4ecb\u8d28\u7edd\u7f18\u6750\u6599\uff09\u4e2d\u7684\u7535\u8377\u4f20\u8f93\u52a8\u529b\u5b66\uff0c\u5e76\u4f7f\u7528\u53cc\u6781\u6027\u7535\u8377\u4f20\u8f93\u6a21\u578b\u6765\u8ba1\u7b97\u7535\u5b50\u3001\u7a7a\u7a74\u53ca\u5176\u88ab\u6355\u83b7\u6001\u7684\u5bc6\u5ea6\u3002\u653e\u7535\u7535\u6d41\u548c\u7a7a\u95f4\u7535\u8377\u5206\u5e03\u7684\u4eff\u771f\u7ed3\u679c\u4e0e\u5b9e\u9a8c\u6d4b\u91cf\u7ed3\u679c\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("charge_transport_in_polyethylene.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
