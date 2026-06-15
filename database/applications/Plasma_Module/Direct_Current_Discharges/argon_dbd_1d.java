/*
 * argon_dbd_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:02 by COMSOL 6.3.0.290. */
public class argon_dbd_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Direct_Current_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/plas", true);

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").set("left", "-1e-4");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "1e-4", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "1e-4", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "1e-4", 2);
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("f0", "50e3[Hz]");
    model.param().descr("f0", "\u5c04\u9891\u9891\u7387");
    model.param().set("w0", "2*pi*f0");
    model.param().descr("w0", "\u89d2\u9891\u7387");
    model.param().set("dplate", "0.1[m]");
    model.param().descr("dplate", "\u677f\u76f4\u5f84");
    model.param().set("As", "0.25*pi*dplate^2");
    model.param().descr("As", "\u677f\u9762\u79ef");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Vrf", "-750[V]*sin(w0*t)");
    model.component("comp1").variable("var1").descr("Vrf", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").physics("plas").create("ccn1", "ChargeConservation", 1);
    model.component("comp1").physics("plas").feature("ccn1").selection().set(1, 3);
    model.component("comp1").physics("plas").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("plas").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("plas").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("plas").prop("CrossSectionArea").set("A", "As");
    model.component("comp1").physics("plas").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 1);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "Ars+Ars=>e+Ar+Ar+");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", "3.3734e8");
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 1);
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "Ars+Ar=>Ar+Ar");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", 1807);
    model.component("comp1").physics("plas").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ars").set("x0", "1e-11");
    model.component("comp1").physics("plas").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("Ar_1p").set("InitIon", true);
    model.component("comp1").physics("plas").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("plas").feature("pes1")
         .set("SpecifyElectronDensityAndEnergy", "FromElectronImpact");
    model.component("comp1").physics("plas").feature("pes1").set("T", "400[K]");
    model.component("comp1").physics("plas").feature("init1").set("neinit", "1e6");
    model.component("comp1").physics("plas").feature("init1").set("ebarinit", 5);
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 0);
    model.component("comp1").physics("plas").feature("sr1").selection().set(2);
    model.component("comp1").physics("plas").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr1").set("gammai", 0.01);
    model.component("comp1").physics("plas").feature("sr1").set("ebari", 2.5);
    model.component("comp1").physics("plas").create("sr2", "SurfaceReaction", 0);
    model.component("comp1").physics("plas").feature("sr2").selection().set(3);
    model.component("comp1").physics("plas").feature("sr2").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("plas").feature("sr2").set("gammai", "1E-6");
    model.component("comp1").physics("plas").feature("sr2").set("ebari", 2.5);
    model.component("comp1").physics("plas").create("sr3", "SurfaceReaction", 0);
    model.component("comp1").physics("plas").feature("sr3").selection().set(2, 3);
    model.component("comp1").physics("plas").feature("sr3").set("formula", "Ars=>Ar");
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 0);
    model.component("comp1").physics("plas").feature("wall1").selection().set(2, 3);
    model.component("comp1").physics("plas").create("sca1", "SurfaceChargeAccumulation", 0);
    model.component("comp1").physics("plas").feature("sca1").selection().set(2, 3);
    model.component("comp1").physics("plas").create("gnd1", "Ground", 0);
    model.component("comp1").physics("plas").feature("gnd1").selection().set(4);
    model.component("comp1").physics("plas").create("term1", "Terminal", 0);
    model.component("comp1").physics("plas").feature("term1").selection().set(1);
    model.component("comp1").physics("plas").feature("term1").set("TerminalName", "electrode");
    model.component("comp1").physics("plas").feature("term1").set("V0", "Vrf");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7535\u4ecb\u8d28 1");
    model.component("comp1").material("mat1").selection().set(1, 3);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"10"});

    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 200);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,5.0e-7,1.0e-4)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"V"});
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg3").label("\u7535\u52bf (plas)");
    model.result("pg1").run();
    model.result().dataset().create("par1", "Parametric1D");
    model.result().dataset("par1").set("seplevels", false);
    model.result().dataset("par1").set("levelscale", "50e3");
    model.result().dataset().duplicate("par2", "par1");
    model.result().dataset("par2").setIndex("looplevelinput", "manual", 0);
    model.result().dataset("par2")
         .setIndex("looplevel", new int[]{21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201}, 0);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u53d7\u6fc0\u6c29\u8d28\u91cf\u5206\u6570");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "plas.wArs");
    model.result("pg4").feature("surf1").set("descr", "\u8d28\u91cf\u5206\u6570");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u52bf");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "V");
    model.result("pg5").feature("surf1").set("descr", "\u7535\u52bf");
    model.result("pg5").feature("surf1").set("colortable", "Dipole");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u573a");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "plas.Ex");
    model.result("pg6").feature("surf1").set("descr", "\u7535\u573a\uff0cx \u5206\u91cf");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "plas.ne");
    model.result("pg7").feature("surf1").set("descr", "\u7535\u5b50\u5bc6\u5ea6");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u7535\u5b50\u5e73\u5747\u80fd\u91cf");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "plas.ebar");
    model.result("pg8").feature("surf1").set("descr", "\u7535\u5b50\u5e73\u5747\u80fd\u91cf");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u7535\u5b50\u6d41\u5bc6\u5ea6");
    model.result("pg9").set("data", "par2");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("expr", "plas.Jelx");
    model.result("pg9").feature("surf1").set("descr", "\u7535\u5b50\u6d41\u5bc6\u5ea6\uff0cx \u5206\u91cf");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u6c29\u79bb\u5b50\u6d41\u5bc6\u5ea6");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("expr", "plas.Jix_wAr_1p");
    model.result("pg10").feature("surf1").set("descr", "\u79bb\u5b50\u6d41\u5bc6\u5ea6\uff0cx \u5206\u91cf");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u603b\u4f20\u5bfc\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg11").run();
    model.result("pg11").feature("surf1").set("expr", "plas.Jix_wAr_1p+plas.Jelx");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u7ec8\u7aef\u7535\u6d41");
    model.result("pg12").create("glob1", "Global");
    model.result("pg12").feature("glob1").set("markerpos", "datapoints");
    model.result("pg12").feature("glob1").set("linewidth", "preference");
    model.result("pg12").feature("glob1").set("expr", new String[]{"plas.I_electrode"});
    model.result("pg12").feature("glob1").set("descr", new String[]{"\u7535\u6d41\uff0cTerminal  electrode"});
    model.result("pg12").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u603b\u529f\u7387\u6c89\u79ef");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").set("expr", new String[]{"plas.Pcap_tot"});
    model.result("pg13").feature("glob1")
         .set("descr", new String[]{"\u603b\u7535\u5bb9\u529f\u7387\u6c89\u79ef\uff0c\u7535\u5b50"});
    model.result("pg13").feature("glob1").set("unit", new String[]{"W"});
    model.result("pg13").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").setIndex("expr", "timeavg(1e-5,2e-5,plas.Pcap_tot,'nointerp')", 0);
    model.result().numerical("gev1").setIndex("descr", "\u5468\u671f 2", 0);
    model.result().numerical("gev1").setIndex("expr", "timeavg(2e-5,3e-5,plas.Pcap_tot,'nointerp')", 1);
    model.result().numerical("gev1").setIndex("descr", "\u5468\u671f 3", 1);
    model.result().numerical("gev1").setIndex("expr", "timeavg(3e-5,4e-5,plas.Pcap_tot,'nointerp')", 2);
    model.result().numerical("gev1").setIndex("descr", "\u5468\u671f 4", 2);
    model.result().numerical("gev1").setIndex("expr", "timeavg(4e-5,5e-5,plas.Pcap_tot,'nointerp')", 3);
    model.result().numerical("gev1").setIndex("descr", "\u5468\u671f 5", 3);
    model.result().numerical("gev1").setIndex("expr", "timeavg(5e-5,6e-5,plas.Pcap_tot,'nointerp')", 4);
    model.result().numerical("gev1").setIndex("descr", "\u5468\u671f 6", 4);
    model.result().numerical("gev1").setIndex("expr", "timeavg(6e-5,7e-5,plas.Pcap_tot,'nointerp')", 5);
    model.result().numerical("gev1").setIndex("descr", "\u5468\u671f 7", 5);
    model.result().numerical("gev1").setIndex("expr", "timeavg(7e-5,8e-5,plas.Pcap_tot,'nointerp')", 6);
    model.result().numerical("gev1").setIndex("descr", "\u5468\u671f 8", 6);
    model.result().numerical("gev1").setIndex("expr", "timeavg(8e-5,9e-5,plas.Pcap_tot,'nointerp')", 7);
    model.result().numerical("gev1").setIndex("descr", "\u5468\u671f 9", 7);
    model.result().numerical("gev1").setIndex("expr", "timeavg(9e-5,10e-5,plas.Pcap_tot,'nointerp')", 8);
    model.result().numerical("gev1").setIndex("descr", "\u5468\u671f 10", 8);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.title("\u4ecb\u8d28\u963b\u6321\u653e\u7535");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6c42\u89e3 kHz \u8303\u56f4\u5185\u7684\u4e00\u7ef4\u4ecb\u7535\u963b\u6321\u653e\u7535\u95ee\u9898\uff0c\u6f14\u793a\u5982\u4f55\u6784\u5efa\u4e00\u4e2a\u4f7f\u7528\u8986\u76d6\u6709\u4ecb\u8d28\u7684\u7535\u6781\u6765\u6fc0\u53d1\u7b49\u79bb\u5b50\u4f53\u7684\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("argon_dbd_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
