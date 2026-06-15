/*
 * quadrupole_mass_filter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class quadrupole_mass_filter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("re", "2.78[mm]", "\u7535\u6781\u534a\u5f84");
    model.param().set("r0", "re/1.147", "\u5185\u5207\u5706\u534a\u5f84");
    model.param().set("rsrc", "1[mm]", "\u6e90\u534a\u5f84");
    model.param().set("rcase", "4*r0", "\u58f3\u534a\u5f84");
    model.param().set("a", "q*eta", "Mathieu \u7cfb\u6570");
    model.param().set("q", "0.7", "Mathieu \u7cfb\u6570");
    model.param().set("eta", "0.2/0.7", "\u6bd4\u7387 a/q \u7684\u659c\u7387");
    model.param().set("f", "4[MHz]", "\u9891\u7387");
    model.param().set("omega", "2*pi*f", "\u89d2\u9891\u7387");
    model.param().set("mi", "40[amu]", "\u6c29\u79bb\u5b50\u8d28\u91cf");
    model.param().set("Vac", "q*mi*omega^2*r0^2/(4*e_const)", "\u5916\u52a0\u4ea4\u6d41\u7535\u538b\u5e45\u503c");
    model.param().set("Udc", "a*mi*omega^2*r0^2/(8*e_const)", "\u5916\u52a0\u76f4\u6d41\u7535\u538b\u5e45\u503c");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "rcase");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "re");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"re+r0", "0"});
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", "re");
    model.component("comp1").geom("geom1").feature("c3").set("pos", new String[]{"0", "re+r0"});
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").set("r", "re");
    model.component("comp1").geom("geom1").feature("c4").set("pos", new String[]{"-(re+r0)", "0"});
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").create("c5", "Circle");
    model.component("comp1").geom("geom1").feature("c5").set("r", "re");
    model.component("comp1").geom("geom1").feature("c5").set("pos", new String[]{"0", "-(re+r0)"});
    model.component("comp1").geom("geom1").run("c5");
    model.component("comp1").geom("geom1").create("c6", "Circle");
    model.component("comp1").geom("geom1").feature("c6").set("r", "rsrc");
    model.component("comp1").geom("geom1").run("c6");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c2", "c3", "c4", "c5");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Perfect vacuum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "0[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6b63\u6781");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(7, 8, 9, 10, 14, 15, 18, 19);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u8d1f\u6781");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(3, 4, 5, 6, 21, 22, 23, 24);

    model.component("comp1").physics("es").prop("EquationForm").setIndex("form", "Stationary", 0);
    model.component("comp1").physics("es").field("electricpotential").field("U");
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().named("sel1");
    model.component("comp1").physics("es").feature("pot1").set("V0", "Udc");
    model.component("comp1").physics("es").create("pot2", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot2").selection().named("sel2");
    model.component("comp1").physics("es").feature("pot2").set("V0", "-Udc");
    model.component("comp1").physics("ec").field("electricpotential").field("V");
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot1").selection().named("sel1");
    model.component("comp1").physics("ec").feature("pot1").set("V0", "Vac");
    model.component("comp1").physics("ec").create("pot2", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot2").selection().named("sel2");
    model.component("comp1").physics("ec").feature("pot2").set("V0", "-Vac");
    model.component("comp1").physics("cpt").feature("pp1").set("Z", 1);
    model.component("comp1").physics("cpt").feature("pp1").set("mp", "mi");
    model.component("comp1").physics("cpt").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("cpt").create("rel1", "Release", 2);
    model.component("comp1").physics("cpt").feature("rel1").selection().set(2);
    model.component("comp1").physics("cpt").feature("rel1").setIndex("rt", "range(0,2.5e-8,2.5e-7)", 0);
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 2);
    model.component("comp1").physics("cpt").feature("ef1").selection().all();
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("cpt").feature("ef1").set("UsePPR", true);
    model.component("comp1").physics("cpt").create("ef2", "ElectricForce", 2);
    model.component("comp1").physics("cpt").feature("ef2").selection().all();
    model.component("comp1").physics("cpt").feature("ef2").set("E_src", "root.comp1.ec.Ex");
    model.component("comp1").physics("cpt").feature("ef2").set("TimeDependenceOfField", "TimeHarmonic");
    model.component("comp1").physics("cpt").feature("ef2").set("UsePPR", true);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "re", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "re", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "q", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.1 0.5 range(0.6,0.2/10,0.8) 1", 0);
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("plist", "4E6");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,2.5e-7,4.0e-5)");
    model.study("std1").feature("time").setSolveFor("/physics/es", false);
    model.study("std1").feature("time").setSolveFor("/physics/ec", false);
    model.study("std1").feature("time").set("usesol", true);
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 161, 0);
    model.result("pg1").setIndex("looplevel", 14, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (es)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 161, 0);
    model.result("pg2").setIndex("looplevel", 14, 1);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "es.normE");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u52bf (ec)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 161, 0);
    model.result("pg3").setIndex("looplevel", 14, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "V");
    model.result("pg3").feature("surf1").set("colortable", "Dipole");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "V");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (ec)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 161, 0);
    model.result("pg4").setIndex("looplevel", 14, 1);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "ec.normE");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("solutionparams", "parent");
    model.result("pg4").feature("str1").set("expr", new String[]{"ec.Ex", "ec.Ey"});
    model.result("pg4").feature("str1").set("titletype", "none");
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("udist", 0.02);
    model.result("pg4").feature("str1").set("maxlen", 0.4);
    model.result("pg4").feature("str1").set("maxsteps", 5000);
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("inheritcolor", false);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("str1").set("data", "parent");
    model.result("pg4").feature("str1").selection().geom("geom1", 1);
    model.result("pg4").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
    model.result("pg4").feature("str1").set("inheritplot", "surf1");
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol3");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "part1");
    model.result("pg5").setIndex("looplevel", 161, 0);
    model.result("pg5").setIndex("looplevel", 14, 1);
    model.result("pg5").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg5").create("traj1", "ParticleTrajectories");
    model.result("pg5").feature("traj1").set("pointtype", "point");
    model.result("pg5").feature("traj1").set("linetype", "none");
    model.result("pg5").feature("traj1").create("col1", "Color");
    model.result("pg5").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg1").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 1);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 3, 1);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 8, 1);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 14, 1);
    model.result("pg5").run();
    model.result().dataset().duplicate("part2", "part1");
    model.result().dataset("part2").selection().geom("geom1", 1);
    model.result().dataset("part2").selection().all();
    model.result().dataset("part2").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 13, 14, 15, 18, 19, 20, 21, 22, 23, 24);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u4f20\u8f93\u6982\u7387");
    model.result("pg6").set("data", "part2");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "1-cpt.alpha", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u4f20\u8f93\u6982\u7387", 0);
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").run();
    model.result("pg5").run();

    model.title("\u56db\u6781\u6ee4\u8d28\u5668");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u56db\u6781\u79bb\u5b50\u9631\u7684\u5de5\u4f5c\u539f\u7406\uff0c\u8fd9\u662f\u56db\u6781\u8d28\u8c31\u4eea\u4e2d\u7684\u5173\u952e\u90e8\u4ef6\uff0c\u5176\u4e2d\u5305\u542b\u7535\u573a\u7684\u4ea4\u6d41\u5206\u91cf\u548c\u76f4\u6d41\u5206\u91cf\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u7c92\u5b50\u8ffd\u8e2a\u6a21\u5757\u201d\u3002");

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
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();

    model.label("quadrupole_mass_filter.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
