/*
 * boltzmann_global_model_argon.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:04 by COMSOL 6.3.0.290. */
public class boltzmann_global_model_argon {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Global_Modeling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");

    model.study().create("std1");
    model.study("std1").create("edfi", "EEDFInitialization");
    model.study("std1").feature("edfi").set("solnum", "auto");
    model.study("std1").feature("edfi").set("notsolnum", "auto");
    model.study("std1").feature("edfi").set("outputmap", new String[]{});
    model.study("std1").feature("edfi").set("ngenAUX", "1");
    model.study("std1").feature("edfi").set("goalngenAUX", "1");
    model.study("std1").feature("edfi").set("ngenAUX", "1");
    model.study("std1").feature("edfi").set("goalngenAUX", "1");
    model.study("std1").feature("edfi").setSolveFor("/physics/plas", true);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"rad", "gap"});

    model.param().set("rad", "0.4[cm]");
    model.param().set("gap", "0.4[cm]");
    model.param().set("Res", "1e5[ohm]");
    model.param().set("Tg", "300[K]");
    model.param().set("P0", "100[torr]");
    model.param().set("Vdc", "1000[V]");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Ldiff", "((pi/gap)^2+(2.405/rad)^2)^-0.5");
    model.component("comp1").variable("var1").set("Ip", "e_const*Area*plas.ne*plas.EN*plas.muN");
    model.component("comp1").variable("var1").set("Vp", "Vdc-Res*Ip");
    model.component("comp1").variable("var1")
         .set("EN", "Vdc/(gap+Res*e_const*Area*plas.ne*nojac(plas.muN)/plas.Nn)/plas.Nn");
    model.component("comp1").variable("var1").set("Area", "pi*rad^2");

    model.component("comp1").physics("plas").prop("DiffusionModel").set("DiffusionModel", "GlobalModel");
    model.component("comp1").physics("plas").prop("ElectronProperties")
         .set("MeanElectronEnergyModel", "LocalFieldApproximationE");
    model.component("comp1").physics("plas").prop("EEDFSettings").set("eedf", "BoltzmannTwoTerm");
    model.component("comp1").physics("plas").prop("EEDFSettings").set("emax", "50[V]");
    model.component("comp1").physics("plas").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("plas").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("plas").create("eir6", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir6").set("formula", "Ar++e+e=>Ar+e");
    model.component("comp1").physics("plas").feature("eir6").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir6").set("de", -15.8);
    model.component("comp1").physics("plas").feature("eir6")
         .set("kf", "8.75e-27[cm^6/s]*(plas.Te/1[V])^-4.5*N_A_const*N_A_const");
    model.component("comp1").physics("plas").create("eir7", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir7").set("formula", "Ar2++e=>Ars+Ar");
    model.component("comp1").physics("plas").feature("eir7").set("type", "Excitation");
    model.component("comp1").physics("plas").feature("eir7").set("de", -3);
    model.component("comp1").physics("plas").feature("eir7")
         .set("kf", "8.5e-7[cm^3/s]*(plas.Te*11600[K/V]/300[K])^-0.67*N_A_const");
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "Ar++Ar+Ar=>Ar2++Ar");
    model.component("comp1").physics("plas").feature("rxn1")
         .set("kf", "2.25e-31[cm^6/s]*(Tg/300[K])^-0.4*N_A_const*N_A_const");
    model.component("comp1").physics("plas").feature().duplicate("rxn2", "rxn1");
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "Ars+Ar+Ar=>Ar+Ar+Ar");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", "1.4e-32[cm^6/s]*N_A_const*N_A_const");
    model.component("comp1").physics("plas").feature().duplicate("rxn3", "rxn2");
    model.component("comp1").physics("plas").feature("rxn3").set("formula", "Ars+Ars=>Ar2++e");
    model.component("comp1").physics("plas").feature("rxn3").set("kf", "6e-10[cm^3/s]*N_A_const");
    model.component("comp1").physics("plas").feature().duplicate("rxn4", "rxn3");
    model.component("comp1").physics("plas").feature("rxn4").set("formula", "Ar2++Ar=>Ar++Ar+Ar");
    model.component("comp1").physics("plas").feature("rxn4")
         .set("kf", "6.06e-6[K*cm^3/s]/Tg*exp(-15130[K]/Tg)*N_A_const");
    model.component("comp1").physics("plas").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ar_1p").set("n0", "1e6[1/cm^3]");
    model.component("comp1").physics("plas").feature("Ar2_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ar2_1p").set("n0", "1E1[1/cm^3]");
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").selection().all();
    model.component("comp1").physics("plas").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr1").set("SpecifyReactionUsing", "UseStickingDiffusion");
    model.component("comp1").physics("plas").feature("sr1").set("LambdaDiff", "Ldiff");
    model.component("comp1").physics("plas").feature().duplicate("sr2", "sr1");
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Ar2+=>Ar+Ar");
    model.component("comp1").physics("plas").feature().duplicate("sr3", "sr2");
    model.component("comp1").physics("plas").feature("sr3").set("formula", "Ars=>Ar");
    model.component("comp1").physics("plas").feature("pes1").set("Tgm", "Tg");
    model.component("comp1").physics("plas").feature("pes1").set("p0", "P0");
    model.component("comp1").physics("plas").feature("pes1").set("Erd", "EN");
    model.component("comp1").physics("plas").feature("pes1").set("ebarEEDFinitgm", "5[V]");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").set("solution", "sol1");
    model.result().dataset("dset2").set("geom", "plas_eedf_xdim");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("ylog", true);
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("xlabel", "Scaled energy coordinate at reactor center");
    model.result("pg1").set("ylabel", "EEDF at reactor center");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", new String[]{"plas.xeedf"});
    model.result("pg1").feature("lngr1").selection().all();
    model.result("pg1").feature("lngr1").label("Two-term Boltzmann");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("autosolution", false);
    model.result("pg1").feature("lngr1").set("autoplotlabel", true);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"plas.fcap"});
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", new String[]{"plas.xeedf"});
    model.result("pg1").feature("lngr2").selection().all();
    model.result("pg1").feature("lngr2").set("expr", new String[]{"plas.fmax"});
    model.result("pg1").feature("lngr2").label("Maxwellian");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("autosolution", false);
    model.result("pg1").feature("lngr2").set("autoplotlabel", true);
    model.result("pg1").create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").set("xdata", "expr");
    model.result("pg1").feature("lngr3").set("xdataexpr", new String[]{"plas.xeedf"});
    model.result("pg1").feature("lngr3").selection().all();
    model.result("pg1").feature("lngr3").set("expr", new String[]{"plas.fdruy"});
    model.result("pg1").feature("lngr3").label("Druyvesteyn");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("autosolution", false);
    model.result("pg1").feature("lngr3").set("autoplotlabel", true);
    model.result("pg1").create("lngr4", "LineGraph");
    model.result("pg1").feature("lngr4").set("xdata", "expr");
    model.result("pg1").feature("lngr4").set("xdataexpr", new String[]{"plas.xeedf"});
    model.result("pg1").feature("lngr4").selection().all();
    model.result("pg1").feature("lngr4").set("expr", new String[]{"plas.fgen"});
    model.result("pg1").feature("lngr4").label("Generalized (g=3)");
    model.result("pg1").feature("lngr4").set("legend", true);
    model.result("pg1").feature("lngr4").set("autosolution", false);
    model.result("pg1").feature("lngr4").set("autoplotlabel", true);
    model.result("pg1").label("\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570 (plas)");
    model.result("pg1").run();
    model.result("pg1").label("EEDF \u521d\u59cb\u5316");
    model.result("pg1").set("xlabel", "\u80fd\u91cf (eV)");
    model.result("pg1").set("ylabel", "EEDF (eV <sup>-3/2</sup>)");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 40);
    model.result("pg1").set("ymin", "1e-12");
    model.result("pg1").set("ymax", 0.5);
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/plas", true);
    model.study("std2").feature("time").set("tlist", "10^{range(log10(1.0e-10),1/20,log10(0.001))}");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7269\u8d28\u5bc6\u5ea6\u548c E/N \u968f\u65f6\u95f4\u7684\u6f14\u53d8");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "plas.ne", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "1/cm^3", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "ne", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "plas.n_wAr_1p", 1);
    model.result("pg2").feature("glob1").setIndex("unit", "1/cm^3", 1);
    model.result("pg2").feature("glob1").setIndex("descr", "Ar+", 1);
    model.result("pg2").feature("glob1").setIndex("expr", "plas.n_wAr2_1p", 2);
    model.result("pg2").feature("glob1").setIndex("unit", "1/cm^3", 2);
    model.result("pg2").feature("glob1").setIndex("descr", "Ar2+", 2);
    model.result("pg2").feature("glob1").setIndex("expr", "plas.n_wArs", 3);
    model.result("pg2").feature("glob1").setIndex("unit", "1/cm^3", 3);
    model.result("pg2").feature("glob1").setIndex("descr", "Ars", 3);
    model.result("pg2").run();
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").setIndex("expr", "EN", 0);
    model.result("pg2").feature("glob2").setIndex("unit", "Td", 0);
    model.result("pg2").feature("glob2").setIndex("descr", "E/N", 0);
    model.result("pg2").run();
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5bc6\u5ea6 (cm<sup>-3</sup>)");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", "1e-9");
    model.result("pg2").set("xmax", "1e-3");
    model.result("pg2").set("ymin", "1e4");
    model.result("pg2").set("ymax", "1e14");
    model.result("pg2").set("yminsec", 0);
    model.result("pg2").set("ymaxsec", 80);
    model.result("pg2").set("xlog", true);
    model.result("pg2").set("ylog", true);
    model.result("pg2").set("legendpos", "middleleft");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "Vp", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "Vp", 0);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").setIndex("expr", "Ip", 0);
    model.result("pg3").feature("glob2").setIndex("unit", "mA", 0);
    model.result("pg3").feature("glob2").setIndex("descr", "Ip", 0);
    model.result("pg3").run();
    model.result("pg3")
         .label("\u7b49\u79bb\u5b50\u4f53\u7535\u538b\u548c\u7535\u6d41\u968f\u65f6\u95f4\u7684\u6f14\u53d8");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u538b (V)");
    model.result("pg3").set("yseclabelactive", true);
    model.result("pg3").set("yseclabel", "\u7535\u6d41 (mA)");
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", "1e-9");
    model.result("pg3").set("xmax", "1e-3");
    model.result("pg3").set("ymin", -100);
    model.result("pg3").set("ymax", 1100);
    model.result("pg3").set("yminsec", -1);
    model.result("pg3").set("ymaxsec", 11);
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("legendpos", "middleleft");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u5b50\u5e73\u5747\u80fd\u91cf\u968f\u65f6\u95f4\u7684\u6f14\u53d8");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", "1e-10");
    model.result("pg4").set("xmax", "1e-3");
    model.result("pg4").set("ymin", 0);
    model.result("pg4").set("ymax", 10);
    model.result("pg4").set("xlog", true);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "plas.ebarB", 0);
    model.result("pg4").run();
    model.result().dataset().duplicate("dset4", "dset3");
    model.result().dataset("dset4").set("comp", "plas_eedf_xdim");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("EEDF \u968f\u65f6\u95f4\u7684\u6f14\u53d8");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{1, 61, 81, 101, 141}, 0);
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u80fd\u91cf (eV)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "EEDF (eV <sup>-3/2</sup>)");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("xmin", 0);
    model.result("pg5").set("xmax", 40);
    model.result("pg5").set("ymin", "1e-12");
    model.result("pg5").set("ymax", 0.5);
    model.result("pg5").set("ylog", true);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", "plas.fcap");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "plas.xeedf");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").run();

    model.title("\u5168\u5c40\u6a21\u578b\u4e0e\u4e24\u9879\u73bb\u5c14\u5179\u66fc\u65b9\u7a0b\u8026\u5408");

    model
         .description("\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570 (EEDF) \u5728\u6574\u4e2a\u653e\u7535\u8fc7\u7a0b\u4e2d\u8d77\u7740\u91cd\u8981\u7684\u4f5c\u7528\u3002\u672c\u4f8b\u7814\u7a76\u6c29\u7b49\u79bb\u5b50\u4f53\u7684\u5f62\u6210\u5468\u671f\uff0c\u5c24\u5176\u662f EEDF\u3002\u5728 100\u00a0mTorr \u538b\u5f3a\u4e0b\uff0c\u901a\u8fc7 1\u00a0kV \u76f4\u6d41\u7535\u6e90\u7535\u538b\u5728 4\u00a0cm \u95f4\u9699\u5185\u4ea7\u751f\u7b49\u79bb\u5b50\u4f53\uff1b\u5e76\u91c7\u7528\u5c40\u90e8\u573a\u8fd1\u4f3c\u7684\u5168\u5c40\u6a21\u578b\u63cf\u8ff0\u7b49\u79bb\u5b50\u4f53\u7269\u8d28\u968f\u65f6\u95f4\u7684\u53d8\u5316\u60c5\u51b5\u3002\u901a\u8fc7\u4f7f\u7528\u4e24\u9879\u8fd1\u4f3c\u6c42\u89e3\u73bb\u5c14\u5179\u66fc\u65b9\u7a0b\uff0c\u5728\u6bcf\u4e2a\u65f6\u6b65\u8ba1\u7b97\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\uff1b\u5e76\u5bf9\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\u8fdb\u884c\u9002\u5f53\u7684\u6a2a\u622a\u9762\u79ef\u5206\uff0c\u4ece\u800c\u5f97\u5230\u7535\u5b50\u78b0\u649e\u53cd\u5e94\u548c\u7535\u5b50\u8fc1\u79fb\u7387\u7684\u901f\u7387\u7cfb\u6570\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("boltzmann_global_model_argon.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
