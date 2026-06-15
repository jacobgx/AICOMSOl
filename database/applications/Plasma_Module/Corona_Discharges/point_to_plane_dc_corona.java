/*
 * point_to_plane_dc_corona.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:02 by COMSOL 6.3.0.290. */
public class point_to_plane_dc_corona {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Corona_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("plas", "ColdPlasma", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/plas", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{5, 10});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("e1").set("semiaxes", new String[]{"0.5[mm]", "5[mm]"});
    model.component("comp1").geom("geom1").feature("e1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("e1").set("pos", new int[]{0, 10});
    model.component("comp1").geom("geom1").feature("e1").set("rot", 180);
    model.component("comp1").geom("geom1").feature().duplicate("e2", "e1");
    model.component("comp1").geom("geom1").feature("e2").set("semiaxes", new String[]{"3[mm]", "10.5[mm]"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("e2", "r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("e1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("dif1", 1);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 7);
    model.component("comp1").geom("geom1").run("mce1");

    model.param().set("V0", "-5[kV]");
    model.param().descr("V0", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("ve", "mueN(plas.Erd)*plas.Erd", "\u7535\u5b50\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("Ratt2", "eta2(plas.Erd)*plas.Nn^2*ve/plas.c_wA", "\u4e09\u4f53\u8fde\u63a5\u4ef6\u7684\u901f\u7387\u5e38\u6570");
    model.component("comp1").variable("var1")
         .set("Ratt1", "eta1(plas.Erd)*plas.Nn*ve/plas.c_wA", "\u8fde\u63a5\u4ef6\u7684\u901f\u7387\u5e38\u6570");
    model.component("comp1").variable("var1")
         .set("Ri", "alpha*ve/plas.c_wA", "\u7535\u79bb\u7684\u901f\u7387\u5e38\u6570");
    model.component("comp1").variable("var1").set("alpha", "alpha(plas.Erd)*plas.Nn", "\u6c64\u68ee\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("betaep", "5e-8[cm^3/s]*N_A_const", "\u7535\u5b50-\u79bb\u5b50\u590d\u5408");
    model.component("comp1").variable("var1")
         .set("betapn", "2e-6[cm^3/s]*N_A_const", "\u79bb\u5b50-\u79bb\u5b50\u590d\u5408");
    model.component("comp1").variable("var1").set("DeN", "1800[cm^2/s]*plas.Nn", "\u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var1").set("ne0", "1e10[m^-3]", "\u521d\u59cb\u7535\u5b50\u6570\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("ni0", "1e16[m^-3]", "\u521d\u59cb\u79bb\u5b50\u6570\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("p0", "760[torr]", "\u6c14\u538b");
    model.component("comp1").variable("var1").set("t0", "300[K]", "\u6c14\u4f53\u6e29\u5ea6");
    model.component("comp1").variable("var1")
         .set("Vapp", "V0*ramp", "\u5177\u6709\u659c\u5761\u51fd\u6570\u7684\u5916\u52a0\u7535\u538b");
    model.component("comp1").variable("var1").set("ramp", "tanh(1e3[1/s]*t)", "\u659c\u5761\u51fd\u6570");
    model.component("comp1").variable("var1")
         .set("muiN", "6e21[1/(V*s*m)]", "\u7ea6\u5316\u79bb\u5b50\u8fc1\u79fb\u7387");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("alpha");
    model.component("comp1").func("an1").set("funcname", "alpha");
    model.component("comp1").func("an1").set("expr", "1.4e-20*exp(-660/x)");
    model.component("comp1").func("an1").set("fununit", "m^2");
    model.component("comp1").func("an1").setIndex("argunit", "Td", 0);
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").label("eta1");
    model.component("comp1").func("an2").set("funcname", "eta1");
    model.component("comp1").func("an2").set("expr", "6e-23*exp(-100/x)");
    model.component("comp1").func("an2").set("fununit", "m^2");
    model.component("comp1").func("an2").setIndex("argunit", "Td", 0);
    model.component("comp1").func("an2").setIndex("plotargs", "10000[Td]", 0, 2);
    model.component("comp1").func().create("an3", "Analytic");
    model.component("comp1").func("an3").label("eta2");
    model.component("comp1").func("an3").set("funcname", "eta2");
    model.component("comp1").func("an3").set("expr", "1.6e-37*(x)^-1.1");
    model.component("comp1").func("an3").set("fununit", "cm^5");
    model.component("comp1").func("an3").setIndex("argunit", "Td", 0);
    model.component("comp1").func("an3").setIndex("plotargs", "10000[Td]", 0, 2);
    model.component("comp1").func().create("an4", "Analytic");
    model.component("comp1").func("an4").label("mueN");
    model.component("comp1").func("an4").set("funcname", "mueN");
    model.component("comp1").func("an4").set("expr", "3.74e22*(x^-0.25)");
    model.component("comp1").func("an4").set("fununit", "1/V/s/cm");
    model.component("comp1").func("an4").setIndex("argunit", "Td", 0);
    model.component("comp1").func("an4").setIndex("plotargs", "10000[Td]", 0, 2);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u58c1");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(2, 5);

    model.component("comp1").physics("plas").prop("ElectronProperties")
         .set("MeanElectronEnergyModel", "LocalFieldApproximationE");
    model.component("comp1").physics("plas").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("plas").prop("ShapeProperty").set("Formulation", "FEMLinear");
    model.component("comp1").physics("plas").prop("InconsistentStabilization").set("IsotropicDiffusionIons", true);
    model.component("comp1").physics("plas").feature("pes1").set("T", "t0");
    model.component("comp1").physics("plas").feature("pes1").set("pA", "p0");
    model.component("comp1").physics("plas").feature("pes1").set("SpecifyElectronDensityAndEnergy", "SpecifyAll");
    model.component("comp1").physics("plas").feature("pes1")
         .set("muN", new String[]{"mueN(plas.Erd)", "0", "0", "0", "mueN(plas.Erd)", "0", "0", "0", "mueN(plas.Erd)"});
    model.component("comp1").physics("plas").feature("pes1")
         .set("DeN", new String[]{"DeN", "0", "0", "0", "DeN", "0", "0", "0", "DeN"});
    model.component("comp1").physics("plas").create("eir1", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir1").set("formula", "e+A=>A++2e");
    model.component("comp1").physics("plas").feature("eir1").set("type", "Ionization");
    model.component("comp1").physics("plas").feature("eir1").set("de", 15);
    model.component("comp1").physics("plas").feature("eir1").set("kf", "Ri");
    model.component("comp1").physics("plas").create("eir2", "ElectronImpactReaction", 2);
    model.component("comp1").physics("plas").feature("eir2").set("formula", "A+e=>A-");
    model.component("comp1").physics("plas").feature("eir2").set("type", "Attachment");
    model.component("comp1").physics("plas").feature("eir2").set("kf", "Ratt1+Ratt2");
    model.component("comp1").physics("plas").create("rxn1", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn1").set("formula", "e+A+=>A");
    model.component("comp1").physics("plas").feature("rxn1").set("kf", "betaep");
    model.component("comp1").physics("plas").create("rxn2", "Reaction", 2);
    model.component("comp1").physics("plas").feature("rxn2").set("formula", "A-+A+=>A+A");
    model.component("comp1").physics("plas").feature("rxn2").set("kf", "betapn");
    model.component("comp1").physics("plas").feature("A").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").feature("A").set("FromMassConstraint", true);
    model.component("comp1").physics("plas").feature("A_1p").set("n0", "ni0");
    model.component("comp1").physics("plas").feature("A_1p").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").feature("A_1m").set("PresetSpeciesData", "N2");
    model.component("comp1").physics("plas").feature("A_1m").set("n0", "ni0");
    model.component("comp1").physics("plas").create("sr1", "SurfaceReaction", 1);
    model.component("comp1").physics("plas").feature("sr1").set("formula", "A+=>A");
    model.component("comp1").physics("plas").feature("sr1").selection().named("sel1");
    model.component("comp1").physics("plas").feature("sr1").set("gammai", 0.05);
    model.component("comp1").physics("plas").feature("sr1").set("ebari", 4);
    model.component("comp1").physics("plas").feature().duplicate("sr2", "sr1");
    model.component("comp1").physics("plas").feature("sr2").set("formula", "A-=>A");
    model.component("comp1").physics("plas").feature("sr2").set("gammai", 0);
    model.component("comp1").physics("plas").feature("sr2").set("ebari", 0);
    model.component("comp1").physics("plas").feature("init1").set("neinit", "ne0");
    model.component("comp1").physics("plas").create("gnd1", "Ground", 1);
    model.component("comp1").physics("plas").feature("gnd1").selection().set(2);
    model.component("comp1").physics("plas").create("mct1", "MetalContact", 1);
    model.component("comp1").physics("plas").feature("mct1").set("V0", "Vapp");
    model.component("comp1").physics("plas").feature("mct1").selection().set(5);
    model.component("comp1").physics("plas").feature("mct1").label("\u91d1\u5c5e\u63a5\u89e6 1\uff0c\u659c\u5761");
    model.component("comp1").physics("plas").create("mct2", "MetalContact", 1);
    model.component("comp1").physics("plas").feature("mct2").set("V0", "V0");
    model.component("comp1").physics("plas").feature("mct2").selection().set(5);
    model.component("comp1").physics("plas").feature("mct2").label("\u91d1\u5c5e\u63a5\u89e6 2\uff0c\u6052\u5b9a");
    model.component("comp1").physics("plas").create("wall1", "WallDriftDiffusion", 1);
    model.component("comp1").physics("plas").feature("wall1").selection().named("sel1");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(1, 5, 6);
    model.component("comp1").mesh("mesh1").feature("size1").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(3, 5, 6);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 150);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 150);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("elemcount", 100);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("elemratio", 80);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("growthrate", "exponential");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis3").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis3").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis3").set("elemcount", 13);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis3").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("table", "plasma");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "10^{range(log10(1.0e-8),1/10,log10(1))}");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"plas/mct2"});
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6 (plas)");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6 (plas)");
    model.result("pg3").label("\u7535\u52bf (plas)");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");
    model.result("pg3").run();
    model.result("pg3").set("data", "mir1");
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").label("\u77ac\u6001 -5 kV");

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/plas", true);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"plas/mct1"});
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "V0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "V0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(-5,-5,-50)", 0);
    model.study("std2").feature("stat").setIndex("punit", "kV", 0);
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"plas.ne"});
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"plas.Te"});
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"V"});
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").label("\u7535\u5b50\u5bc6\u5ea6 (plas) 1");
    model.result("pg5").label("\u7535\u5b50\u6e29\u5ea6 (plas) 1");
    model.result("pg6").label("\u7535\u52bf (plas) 1");
    model.result("pg4").run();
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").set("data", "dset2");
    model.result("pg4").run();
    model.result("pg4").set("data", "mir2");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u7535\u5b50\u6570\u5bc6\u5ea6");
    model.result("pg4").set("paramindicator", "");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg4").feature("surf1").set("rangecoloractive", true);
    model.result("pg4").feature("surf1").set("rangecolormin", "1e13");
    model.result("pg4").feature("surf1").set("rangecolormax", "1e17");
    model.result("pg4").run();
    model.result("pg6").run();
    model.result("pg6").set("data", "mir2");
    model.result("pg4").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").label("\u7a33\u6001\uff0c\u659c\u5347\u81f3 -50 kV");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result().create("pg7", "PlotGroup2D");

    model.nodeGroup("grp2").add("plotgroup", "pg7");

    model.result("pg7").run();
    model.result("pg7").label("\u6b63\u79bb\u5b50\u6570\u5bc6\u5ea6");
    model.result("pg7").set("data", "mir2");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u6b63\u79bb\u5b50\u6570\u5bc6\u5ea6");
    model.result("pg7").set("paramindicator", "");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "plas.n_wA_1p");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg7").feature("surf1").set("rangecoloractive", true);
    model.result("pg7").feature("surf1").set("rangecolormin", "1e7");
    model.result("pg7").feature("surf1").set("rangecolormax", "1e19");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");

    model.nodeGroup("grp2").add("plotgroup", "pg8");

    model.result("pg8").run();
    model.result("pg8").label("\u8d1f\u79bb\u5b50\u6570\u5bc6\u5ea6");
    model.result("pg8").set("title", "\u8d1f\u79bb\u5b50\u6570\u5bc6\u5ea6");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "plas.n_wA_1m");
    model.result("pg8").feature("surf1").set("rangecolormin", "1e14");
    model.result("pg8").feature("surf1").set("rangecolormax", "1e18");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");

    model.nodeGroup("grp2").add("plotgroup", "pg9");

    model.result("pg9").run();
    model.result("pg9").label("\u5e26\u7535\u7269\u8d28\u7684\u6570\u5bc6\u5ea6");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevelinput", "last", 0);
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "z \u5750\u6807 (cm)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u5bc6\u5ea6 (1/m<sup>3</sup>)");
    model.result("pg9").set("axislimits", true);
    model.result("pg9").set("xmin", -0.12);
    model.result("pg9").set("xmax", 9.62);
    model.result("pg9").set("ymin", "1e10");
    model.result("pg9").set("ymax", "2e19");
    model.result("pg9").set("ylog", true);
    model.result("pg9").set("legendpos", "upperleft");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").selection().set(1);
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "z");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").feature("lngr1").set("legendmethod", "manual");
    model.result("pg9").feature("lngr1").setIndex("legends", "\u7535\u5b50", 0);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("lngr2", "lngr1");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").set("expr", "plas.n_wA_1p");
    model.result("pg9").feature("lngr2").setIndex("legends", "\u6b63\u79bb\u5b50", 0);
    model.result("pg9").feature().duplicate("lngr3", "lngr2");
    model.result("pg9").run();
    model.result("pg9").feature("lngr3").set("expr", "plas.n_wA_1m");
    model.result("pg9").feature("lngr3").setIndex("legends", "\u8d1f\u79bb\u5b50", 0);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");

    model.nodeGroup("grp2").add("plotgroup", "pg10");

    model.result("pg10").run();
    model.result("pg10").label("\u7535\u538b vs. \u7535\u6d41");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").set("titletype", "none");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").setIndex("expr", "plas.I_2", 0);
    model.result("pg10").feature("glob1").setIndex("unit", "uA", 0);
    model.result("pg10").feature("glob1").setIndex("descr", "\u7535\u6d41", 0);
    model.result("pg10").feature("glob1").set("xdata", "expr");
    model.result("pg10").feature("glob1").set("xdataexpr", "-V0");
    model.result("pg10").feature("glob1").set("xdataunit", "kV");
    model.result("pg10").feature("glob1").set("xdatadescractive", true);
    model.result("pg10").feature("glob1").set("xdatadescr", "|V0|");
    model.result("pg10").run();
    model.result("pg4").run();
    model.result("pg7").run();
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg8").run();

    model.title("\u7a7a\u6c14\u4e2d\u70b9\u5bf9\u9762\u914d\u7f6e\u7684\u76f4\u6d41\u7535\u6655\u653e\u7535");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u7814\u7a76\u5927\u6c14\u538b\u4e0b\u5e72\u7a7a\u6c14\u4e2d\uff0c\u91c7\u7528\u70b9\u5bf9\u9762\u914d\u7f6e\u7684\u8d1f\u7535\u6655\u653e\u7535\u73b0\u8c61\u3002\u4e00\u4e2a\u6beb\u7c73\u7ea7\u7684\u692d\u5706\u5f62\u7535\u6781\u4ea7\u751f\u4e86\u9ad8\u5f3a\u5ea6\u7684\u7535\u573a\uff0c\u4f7f\u6b64\u5904\u53d1\u751f\u7535\u6655\u653e\u7535\u3002\u63a5\u5730\u5e73\u9762\u653e\u7f6e\u5728\u8ddd\u79bb\u7535\u6655\u7535\u6781 10\u00a0cm \u7684\u4f4d\u7f6e\u3002\u7535\u538b\u5728 -5 \u5230 -50\u00a0kV \u4e4b\u95f4\u53d8\u5316\u3002\u5e26\u7535\u7269\u8d28\u7684\u4ea7\u751f\u548c\u4f20\u8f93\u901a\u8fc7\u4e0e\u6cca\u677e\u65b9\u7a0b\u7684\u81ea\u6d3d\u8026\u5408\u8fdb\u884c\u6c42\u89e3\u3002\u672c\u4f8b\u91c7\u7528\u5c40\u90e8\u573a\u8fd1\u4f3c\uff0c\u5373\u5047\u8bbe\u4f20\u9012\u7cfb\u6570\u548c\u6e90\u7cfb\u6570\u53ef\u901a\u8fc7\u7ea6\u5316\u7535\u573a\u5145\u5206\u53c2\u6570\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("point_to_plane_dc_corona.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
