/*
 * si_inversion_layer_density_gradient_and_schrodinger_poisson.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:51 by COMSOL 6.3.0.290. */
public class si_inversion_layer_density_gradient_and_schrodinger_poisson {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Device_Building_Blocks");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("semie", "SemiconductorEquilibrium");
    model.study("std1").feature("semie").set("ftplistmethod", "manual");
    model.study("std1").feature("semie").set("solnum", "auto");
    model.study("std1").feature("semie").set("notsolnum", "auto");
    model.study("std1").feature("semie").set("outputmap", new String[]{});
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").setSolveFor("/physics/semi", true);

    model.component("comp1").geom("geom1").lengthUnit("nm");
    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("coordsource", "vector");
    model.component("comp1").geom("geom1").feature("i1").set("coordvec", "0, 10, 70, 300, 1e3");

    model.param().label("\u5bc6\u5ea6\u68af\u5ea6\u53c2\u6570");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u6e29\u5ea6");
    model.param().set("mnDG", "me_const/3");
    model.param().descr("mnDG", "\u5bc6\u5ea6\u68af\u5ea6\u6709\u6548\u8d28\u91cf");
    model.param().set("Na0", "3.8e16[1/cm^3]");
    model.param().descr("Na0", "\u63ba\u6742\u6d53\u5ea6");
    model.param().set("Vg", "0[V]");
    model.param().descr("Vg", "\u6805\u6781\u7535\u538b");
    model.param().set("epsrOx", "3.9");
    model.param().descr("epsrOx", "\u6c27\u5316\u7269\u4ecb\u7535\u5e38\u6570");
    model.param().set("dOx", "3.1[nm]");
    model.param().descr("dOx", "\u6c27\u5316\u5c42\u539a\u5ea6");
    model.param().set("Phi0", "4.01[V]");
    model.param().descr("Phi0", "\u6805\u6781\u91d1\u5c5e\u529f\u51fd\u6570");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor_material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("mun", new String[]{"1450[cm^2/(V*s)]"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("mup", new String[]{"500[cm^2/(V*s)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"11.9"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Eg0", new String[]{"1.12[V]"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("chi0", new String[]{"4.05[V]"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nc", new String[]{"2.80e19[cm^-3]"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nv", new String[]{"2.65e19[cm^-3]"});

    model.component("comp1").physics("semi").prop("ModelProperties").set("CarrierStatistics", "FermiDirac");
    model.component("comp1").physics("semi").prop("ShapeProperty").set("Formulation", "FEM2DG");
    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "T0");
    model.component("comp1").physics("semi").feature("smm1")
         .set("meDG", new String[]{"mnDG", "0", "0", "0", "mnDG", "0", "0", "0", "mnDG"});
    model.component("comp1").physics("semi").feature("smm1")
         .set("mhDG", new String[]{"10*me_const", "0", "0", "0", "10*me_const", "0", "0", "0", "10*me_const"});
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm1").selection().all();
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "Na0");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc1").selection().set(5);
    model.component("comp1").physics("semi").create("gc1", "GateContact", 0);
    model.component("comp1").physics("semi").feature("gc1").selection().set(1);
    model.component("comp1").physics("semi").feature("gc1").set("V0", "Vg");
    model.component("comp1").physics("semi").feature("gc1").set("epsilon_ins", "epsrOx");
    model.component("comp1").physics("semi").feature("gc1").set("d_ins", "dOx");
    model.component("comp1").physics("semi").feature("gc1").set("Phi", "Phi0");
    model.component("comp1").physics("semi").feature("gc1").set("DGexteriorBC", "barrier");
    model.component("comp1").physics("semi").feature("gc1").set("Phi_nOx", "1e4[V]");
    model.component("comp1").physics("semi").feature("gc1").set("Phi_pOx", "0[V]");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "1e-4");
    model.component("comp1").mesh("mesh1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size3", 3);
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").feature("size3").set("table", "semi");
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", 0.5);

    model.study("std1").label("\u7814\u7a76 1\uff1a\u5bc6\u5ea6\u68af\u5ea6");
    model.study("std1").feature("semie").set("useparam", true);
    model.study("std1").feature("semie").setIndex("pname", "T0", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "", 0);
    model.study("std1").feature("semie").setIndex("punit", "K", 0);
    model.study("std1").feature("semie").setIndex("pname", "T0", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "", 0);
    model.study("std1").feature("semie").setIndex("punit", "K", 0);
    model.study("std1").feature("semie").setIndex("pname", "Vg", 0);
    model.study("std1").feature("semie").setIndex("plistarr", "-1 0.2 1", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u80fd\u7ea7 (semi)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Energy Diagram");
    model.result("pg1").set("ylabel", "Energy (eV)");
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").label("\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg1").feature("lngr1").set("unit", "eV");
    model.result("pg1").feature("lngr1").set("linecolor", "blue");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").set("legends", new String[]{"Ec"});
    model.result("pg1").feature("lngr1").set("resolution", "norefine");
    model.result("pg1").feature("lngr1").set("smooth", "everywhere");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result("pg1").feature().create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg1").feature("lngr2").set("unit", "eV");
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linecolor", "black");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("legendmethod", "manual");
    model.result("pg1").feature("lngr2").set("legends", new String[]{"Efn"});
    model.result("pg1").feature("lngr2").set("resolution", "norefine");
    model.result("pg1").feature("lngr2").set("smooth", "everywhere");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("data", "parent");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr2").selection().set(1, 2, 3, 4);
    model.result("pg1").feature().create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").label("\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg1").feature("lngr3").set("unit", "eV");
    model.result("pg1").feature("lngr3").set("linestyle", "dotted");
    model.result("pg1").feature("lngr3").set("linecolor", "black");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("legendmethod", "manual");
    model.result("pg1").feature("lngr3").set("legends", new String[]{"Efp"});
    model.result("pg1").feature("lngr3").set("resolution", "norefine");
    model.result("pg1").feature("lngr3").set("smooth", "everywhere");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("data", "parent");
    model.result("pg1").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr3").selection().set(1, 2, 3, 4);
    model.result("pg1").feature().create("lngr4", "LineGraph");
    model.result("pg1").feature("lngr4").label("\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg1").feature("lngr4").set("unit", "eV");
    model.result("pg1").feature("lngr4").set("linecolor", "green");
    model.result("pg1").feature("lngr4").set("legend", true);
    model.result("pg1").feature("lngr4").set("legendmethod", "manual");
    model.result("pg1").feature("lngr4").set("legends", new String[]{"Ev"});
    model.result("pg1").feature("lngr4").set("resolution", "norefine");
    model.result("pg1").feature("lngr4").set("smooth", "everywhere");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("data", "parent");
    model.result("pg1").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr4").selection().set(1, 2, 3, 4);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("ylabel", "Carrier concentration (1/cm^3)");
    model.result("pg2").set("ylog", true);
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").label("\u7535\u5b50\u6d53\u5ea6");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("expr", "semi.N");
    model.result("pg2").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").set("legends", new String[]{"electrons"});
    model.result("pg2").feature("lngr1").set("resolution", "norefine");
    model.result("pg2").feature("lngr1").set("smooth", "everywhere");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result("pg2").feature().create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").label("\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("expr", "semi.P");
    model.result("pg2").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").set("legends", new String[]{"holes"});
    model.result("pg2").feature("lngr2").set("resolution", "norefine");
    model.result("pg2").feature("lngr2").set("smooth", "everywhere");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("data", "parent");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1, 2, 3, 4);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg3").set("ylabel", "Electric Potential (V)");
    model.result("pg3").feature().create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("expr", "V");
    model.result("pg3").feature("lngr1").set("resolution", "norefine");
    model.result("pg3").feature("lngr1").set("smooth", "everywhere");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("data", "parent");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u91cf\u5b50\u52bf (semi)");
    model.result("pg4").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg4").feature().create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").label("\u91cf\u5b50\u52bf\uff0c\u7535\u5b50");
    model.result("pg4").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg4").feature("lngr1").set("expr", "semi.VnDG");
    model.result("pg4").feature("lngr1").set("smooth", "internal");
    model.result("pg4").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg4").feature("lngr1").set("data", "parent");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2, 3, 4);
    model.result("pg4").feature().create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").label("\u91cf\u5b50\u52bf\uff0c\u7a7a\u7a74");
    model.result("pg4").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg4").feature("lngr2").set("expr", "semi.VpDG");
    model.result("pg4").feature("lngr2").set("smooth", "internal");
    model.result("pg4").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg4").feature("lngr2").set("data", "parent");
    model.result("pg4").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr2").selection().set(1, 2, 3, 4);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("xdataexpr", "X");
    model.result("pg5").feature("lngr1").set("expr", "semi.Nnetdop");
    model.result("pg5").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg5").feature("lngr1").set("linecolor", "red");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").set("legends", new String[]{"P \u578b (\u7ea2\u8272)"});
    model.result("pg5").feature("lngr1").feature().create("filt1", "LineGraphFilter");
    model.result("pg5").feature("lngr1").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg5").feature("lngr2").selection().all();
    model.result("pg5").feature("lngr2").set("xdataexpr", "X");
    model.result("pg5").feature("lngr2").set("expr", "semi.Nnetdop");
    model.result("pg5").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg5").feature("lngr2").set("linecolor", "blue");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").set("legends", new String[]{"N \u578b (\u84dd\u8272)"});
    model.result("pg5").feature("lngr2").feature().create("filt1", "LineGraphFilter");
    model.result("pg5").feature("lngr2").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("legendpos", "uppermiddle");
    model.result("pg5")
         .set("ylabel", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert, 1/cm<sup>3</sup>");
    model.result("pg5").set("ylog", true);
    model.result("pg5").feature("lngr1").label("P \u578b");
    model.result("pg5").feature("lngr2").label("N \u578b");
    model.result("pg5").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();
    model.result("pg5").run();
    model.result().remove("pg5");
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u5b50\u6d53\u5ea6\u6bd4\u8f83");
    model.result("pg5").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg5").setIndex("looplevelindices", "2 3", 0);
    model.result("pg5").run();
    model.result("pg5").feature().remove("lngr2");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").selection().set(1, 2);
    model.result("pg5").feature("lngr1").label("\u5bc6\u5ea6\u68af\u5ea6");
    model.result("pg5").feature("lngr1").set("linestyle", "dashed");
    model.result("pg5").feature("lngr1").set("legendmethod", "automatic");
    model.result("pg5").feature("lngr1").set("legendprefix", "DG");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", -1);
    model.result("pg5").set("xmax", 10);
    model.result("pg5").set("ymin", "3e16");
    model.result("pg5").set("ymax", "1e20");

    model.component("comp1").physics().create("schr", "SchrodingerEquation", "geom1");

    model.study("std1").feature("semie").setSolveFor("/physics/schr", false);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study("std1").feature("semie").setSolveFor("/physics/es", false);

    model.component("comp1").multiphysics().create("schrp1", "SchrodingerPoissonCoupling", 1);

    model.study("std1").feature("semie").setSolveFor("/multiphysics/schrp1", false);

    model.component("comp1").multiphysics("schrp1").set("Schr_physics", "schr");
    model.component("comp1").multiphysics("schrp1").set("ES_physics", "es");
    model.component("comp1").multiphysics("schrp1").selection().all();

    model.param().create("par2");
    model.param("par2").label("\u859b\u5b9a\u8c14-\u6cca\u677e\u65b9\u7a0b\u7684\u53c2\u6570");
    model.param("par2").set("nv", "2");
    model.param("par2").descr("nv", "\u8c37\u7b80\u5e76\u6027");
    model.param("par2").set("fm3", "0.916");
    model.param("par2").descr("fm3", "\u7eb5\u5411\u6709\u6548\u8d28\u91cf\u56e0\u5b50");
    model.param("par2").set("fm1", "0.190");
    model.param("par2").descr("fm1", "\u6a2a\u5411\u6709\u6548\u8d28\u91cf\u56e0\u5b50 1");
    model.param("par2").set("fm2", "0.190");
    model.param("par2").descr("fm2", "\u6a2a\u5411\u6709\u6548\u8d28\u91cf\u56e0\u5b50 2");
    model.param("par2").set("m3", "fm3*me_const");
    model.param("par2").descr("m3", "\u7eb5\u5411\u6709\u6548\u8d28\u91cf");
    model.param("par2").set("m1", "fm1*me_const");
    model.param("par2").descr("m1", "\u6a2a\u5411\u6709\u6548\u8d28\u91cf 1");
    model.param("par2").set("m2", "fm2*me_const");
    model.param("par2").descr("m2", "\u6a2a\u5411\u6709\u6548\u8d28\u91cf 2");
    model.param("par2").set("md", "sqrt(m1*m2)");
    model.param("par2").descr("md", "\u6001\u5bc6\u5ea6\u6709\u6548\u8d28\u91cf");

    model.component("comp1").physics("schr").selection().set(1, 2, 3);
    model.component("comp1").physics("es").selection().set(1, 2, 3);
    model.component("comp1").physics("schr").feature("meff1")
         .set("meffe_psi", new String[]{"m3", "0", "0", "0", "m3", "0", "0", "0", "m3"});
    model.component("comp1").physics("schr").feature("ve1").set("Ve_src", "userdef");
    model.component("comp1").physics("schr").feature("ve1").set("Ve", 0);
    model.component("comp1").physics("schr").create("zprb1", "ZeroProbability", 0);
    model.component("comp1").physics("schr").feature("zprb1").selection().set(1);
    model.component("comp1").physics("es").create("ccnf1", "ChargeConservationFluid", 1);
    model.component("comp1").physics("es").feature("ccnf1").selection().all();
    model.component("comp1").physics("es").create("scd1", "SpaceChargeDensity", 1);
    model.component("comp1").physics("es").feature("scd1")
         .label("\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6 1\uff1a\u521d\u59cb\u7535\u5b50\u5bc6\u5ea6");
    model.component("comp1").physics("es").feature("scd1").selection().all();
    model.component("comp1").physics("es").feature("scd1").set("rhoq", "-e_const*semi.N");
    model.component("comp1").physics("es").feature().duplicate("scd2", "scd1");
    model.component("comp1").physics("es").feature("scd2")
         .label("\u7a7a\u95f4\u7535\u8377\u5bc6\u5ea6 2\uff1a\u7a7a\u7a74\u548c\u7535\u79bb\u63ba\u6742\u5242");
    model.component("comp1").physics("es").feature("scd2").set("rhoq", "e_const*(semi.P+semi.Ndplus-semi.Naminus)");
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 0);
    model.component("comp1").physics("es").feature("pot1").selection().set(4);
    model.component("comp1").physics("es").feature("pot1").set("V0", "-semi.Ec");
    model.component("comp1").physics("es").create("df1", "DisplacementField", 0);
    model.component("comp1").physics("es").feature("df1").selection().set(1);
    model.component("comp1").physics("es").feature("df1")
         .set("D0", new String[]{"epsilon0_const*epsrOx*(Vg-(Phi0-semi.chi_semi)-V2)/dOx", "0", "0"});

    model.component("comp1").multiphysics("schrp1").set("minput_temperature", "T0");
    model.component("comp1").multiphysics("schrp1").set("Ef", "e_const*withsol('sol1',semi.Ef_0,setval(Vg,Vg))");
    model.component("comp1").multiphysics("schrp1").set("md", "md");
    model.component("comp1").multiphysics("schrp1").set("gi", "nv");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", false);
    model.study("std2").feature("stat").setSolveFor("/physics/schr", false);
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/schrp1", false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "dOx", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "dOx", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "Vg", 0);
    model.study("std2").feature("param").setIndex("plistarr", 0.2, 0);
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").feature("stat").set("notsolnum", 2);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u859b\u5b9a\u8c14-\u6cca\u677e Vg=0.2V");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("sdpo", "SchrodingerPoisson");
    model.study("std2").feature("sdpo").set("eigmethod", "region");
    model.study("std2").feature("sdpo").set("appnreigs", 100);
    model.study("std2").feature("sdpo").set("maxnreigs", 300);
    model.study("std2").feature("sdpo").set("eigunit", "");
    model.study("std2").feature("sdpo").set("eigsr", -0.1);
    model.study("std2").feature("sdpo").set("eiglr", 1);
    model.study("std2").feature("sdpo").set("eigsi", "-1e-7");
    model.study("std2").feature("sdpo").set("eigli", "1e-7");
    model.study("std2").feature("sdpo").set("useadvanceddisable", true);
    model.study("std2").feature("sdpo").set("disabledphysics", new String[]{"es/scd1"});
    model.study("std2").feature("sdpo").set("method", "minimization_of_global_variable");
    model.study("std2").feature("sdpo").set("expr", "schrp1.global_err");
    model.study("std2").feature("sdpo").set("atolterm", "1e-6");
    model.study("std2").feature("sdpo").set("useparam", true);
    model.study("std2").feature("sdpo").setIndex("pname", "dOx", 0);
    model.study("std2").feature("sdpo").setIndex("plistarr", "", 0);
    model.study("std2").feature("sdpo").setIndex("punit", "m", 0);
    model.study("std2").feature("sdpo").setIndex("pname", "dOx", 0);
    model.study("std2").feature("sdpo").setIndex("plistarr", "", 0);
    model.study("std2").feature("sdpo").setIndex("punit", "m", 0);
    model.study("std2").feature("sdpo").setIndex("pname", "nv", 0);
    model.study("std2").feature("sdpo").setIndex("plistarr", "2 4", 0);
    model.study("std2").feature("sdpo").setIndex("pname", "dOx", 1);
    model.study("std2").feature("sdpo").setIndex("plistarr", "", 1);
    model.study("std2").feature("sdpo").setIndex("punit", "m", 1);
    model.study("std2").feature("sdpo").setIndex("pname", "dOx", 1);
    model.study("std2").feature("sdpo").setIndex("plistarr", "", 1);
    model.study("std2").feature("sdpo").setIndex("punit", "m", 1);
    model.study("std2").feature("sdpo").setIndex("pname", "fm3", 1);
    model.study("std2").feature("sdpo").setIndex("plistarr", "0.916 0.190", 1);
    model.study("std2").feature("sdpo").setIndex("pname", "dOx", 2);
    model.study("std2").feature("sdpo").setIndex("plistarr", "", 2);
    model.study("std2").feature("sdpo").setIndex("punit", "m", 2);
    model.study("std2").feature("sdpo").setIndex("pname", "dOx", 2);
    model.study("std2").feature("sdpo").setIndex("plistarr", "", 2);
    model.study("std2").feature("sdpo").setIndex("punit", "m", 2);
    model.study("std2").feature("sdpo").setIndex("pname", "fm2", 2);
    model.study("std2").feature("sdpo").setIndex("plistarr", "0.190 0.916", 2);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol6");
    model.sol("sol6").study("std2");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol6");
    model.batch("p1").run("compute");

    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("data", "dset2");
    model.result("pg5").feature("lngr2").label("\u859b\u5b9a\u8c14-\u6cca\u677e Vg=0.2V");
    model.result("pg5").feature("lngr2").set("expr", "schrp1.n_sum");
    model.result("pg5").feature("lngr2").set("linestyle", "solid");
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").setIndex("legends", "SP 0.2 V", 0);
    model.result("pg5").run();
    model.result("pg5").feature().copy("lngr3", "pg5/lngr2");
    model.result("pg5").run();
    model.result("pg5").run();

    model.study().create("std3");
    model.study("std3").feature().copy("param", "std2/param");
    model.study("std3").feature().copy("stat", "std2/stat");
    model.study("std3").feature().copy("sdpo", "std2/sdpo");
    model.study("std3").label("\u7814\u7a76 3\uff1a\u859b\u5b9a\u8c14-\u6cca\u677e Vg=1.0V");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("param").setIndex("plistarr", "1.0", 0);
    model.study("std3").feature("stat").set("notsolnum", 3);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol12");
    model.sol("sol12").study("std3");
    model.sol("sol12").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol12");
    model.batch("p2").run("compute");

    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr4", "lngr2");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").label("\u859b\u5b9a\u8c14-\u6cca\u677e Vg=1.0V");
    model.result("pg5").feature("lngr3").set("data", "dset7");
    model.result("pg5").feature("lngr3").setIndex("legends", "SP 1 V", 0);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").feature().copy("lngr3", "pg5/lngr3");
    model.result("pg6").run();
    model.result("pg6").run();

    model
         .title("\u7845\u53cd\u578b\u5c42\u7684\u5bc6\u5ea6\u68af\u5ea6\u548c\u859b\u5b9a\u8c14-\u6cca\u677e\u7ed3\u679c");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5728\u7845\u53cd\u578b\u5c42\u7684\u5668\u4ef6\u7269\u7406\u573a\u4eff\u771f\u4e2d\uff0c\u5982\u4f55\u4f7f\u7528\u5bc6\u5ea6\u68af\u5ea6\u516c\u5f0f\u6765\u5305\u542b\u91cf\u5b50\u9650\u5236\u6548\u5e94\u3002\u8fd9\u79cd\u65b9\u6cd5\u76f8\u8f83\u4e8e\u4f20\u7edf\u7684\u6f02\u79fb-\u6269\u6563\u65b9\u7a0b\uff0c\u53ea\u8981\u6c42\u9002\u5ea6\u589e\u52a0\u8ba1\u7b97\u8d44\u6e90\uff0c\u4f7f\u5f97\u5de5\u7a0b\u7814\u7a76\u7684\u6548\u7387\u8fdc\u9ad8\u4e8e\u5176\u4ed6\u66f4\u590d\u6742\u7684\u91cf\u5b50\u529b\u5b66\u65b9\u6cd5\u3002\u672c\u4f8b\u5c06\u5bc6\u5ea6\u68af\u5ea6\u7406\u8bba\u7684\u7ed3\u679c\u4e0e\u859b\u5b9a\u8c14-\u6cca\u677e\u65b9\u7a0b\u7684\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\uff0c\u4e24\u79cd\u7406\u8bba\u8ba1\u7b97\u51fa\u7684\u7535\u5b50\u5bc6\u5ea6\u5206\u5e03\u5747\u8868\u73b0\u51fa\u9884\u671f\u7684\u91cf\u5b50\u9650\u5236\u884c\u4e3a\uff0c\u5e76\u4e14\u4e0e\u53c2\u8003\u6587\u732e\u4e2d\u53d1\u8868\u7684\u6570\u636e\u9ad8\u5ea6\u543b\u5408\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();

    model.label("si_inversion_layer_density_gradient_and_schrodinger_poisson.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
