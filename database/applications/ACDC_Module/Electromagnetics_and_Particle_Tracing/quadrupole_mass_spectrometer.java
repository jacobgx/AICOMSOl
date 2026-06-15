/*
 * quadrupole_mass_spectrometer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class quadrupole_mass_spectrometer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("re", "2.768[mm]", "\u6746\u534a\u5f84");
    model.param().set("r0", "re/1.147", "\u5185\u5207\u5706\u534a\u5f84");
    model.param().set("rsrc", "0.75[mm]", "\u6e90\u534a\u5f84");
    model.param().set("rcase", "4*re", "\u58f3\u534a\u5f84");
    model.param().set("Lquad", "127[mm]", "\u56db\u6781\u6746\u957f\u5ea6");
    model.param().set("a", "0.05", "Mathieu \u7cfb\u6570");
    model.param().set("q", "0.7", "Mathieu \u7cfb\u6570");
    model.param().set("f", "4[MHz]", "\u9891\u7387");
    model.param().set("omega", "2*pi*f", "\u89d2\u9891\u7387");
    model.param().set("mi", "40[amu]", "\u79bb\u5b50\u8d28\u91cf");
    model.param().set("Vac", "q*mi*omega^2*r0^2/(4*e_const)", "\u4ea4\u6d41\u7535\u538b");
    model.param().set("Udc", "a*mi*omega^2*r0^2/(8*e_const)", "\u76f4\u6d41\u7535\u538b");
    model.param().set("A", "2[V]", "\u52a0\u901f\u7535\u538b");
    model.param().set("vx0", "sqrt(2*e_const*A/mi)", "\u521d\u59cb x \u65b9\u5411\u901f\u5ea6");
    model.param().set("fd", "2[mm]", "\u8fb9\u7f18\u573a\u6df1\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "re");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"-(re+r0)", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "re");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new String[]{"re+r0", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("r", "re");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3")
         .set("pos", new String[]{"0", "-(re+r0)"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4").set("r", "re");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4")
         .set("pos", new String[]{"0", "re+r0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c4");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "Lquad", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", "-fd");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "rcase");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c2").set("r", "rsrc");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c3").set("r", "2*rsrc");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c3");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "fd", 0);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "Lquad", 1);
    model.component("comp1").geom("geom1").feature("ext2").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext2").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext2").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("ext2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("ext1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u6b63\u6781");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(25, 26, 28, 29, 37, 38, 43, 44);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u8d1f\u6781");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(20, 21, 22, 23, 47, 48, 49, 50);

    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot1").selection().named("sel1");
    model.component("comp1").physics("es").feature("pot1").set("V0", "Udc");
    model.component("comp1").physics("es").create("pot2", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot2").selection().named("sel2");
    model.component("comp1").physics("es").feature("pot2").set("V0", "-Udc");
    model.component("comp1").physics("es").create("pot3", "ElectricPotential", 2);
    model.component("comp1").physics("es").feature("pot3").selection().set(1, 4, 7);
    model.component("comp1").physics("es").feature("pot3").set("V0", 3);
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot1").selection().named("sel1");
    model.component("comp1").physics("ec").feature("pot1").set("V0", "Vac");
    model.component("comp1").physics("ec").create("pot2", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot2").selection().named("sel2");
    model.component("comp1").physics("ec").feature("pot2").set("V0", "-Vac");
    model.component("comp1").physics("ec").create("pot3", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot3").selection().set(1, 4, 7);
    model.component("comp1").physics("ec").feature("pot3").set("V0", 3);
    model.component("comp1").physics("cpt").feature("pp1").set("mp", "mi");
    model.component("comp1").physics("cpt").feature("pp1").set("Z", 1);
    model.component("comp1").physics("cpt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("cpt").feature("inl1").selection().set(7);
    model.component("comp1").physics("cpt").feature("inl1").set("InitialPosition", "ProjectedPlaneGrid");
    model.component("comp1").physics("cpt").feature("inl1").setIndex("N", 100, 0);
    model.component("comp1").physics("cpt").feature("inl1").setIndex("rt", "range(0,2.5e-8,2.5e-7)", 0);
    model.component("comp1").physics("cpt").feature("inl1").set("v0", new String[]{"vx0", "0", "0"});
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 3);
    model.component("comp1").physics("cpt").feature("ef1").selection().all();
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Ex");
    model.component("comp1").physics("cpt").create("ef2", "ElectricForce", 3);
    model.component("comp1").physics("cpt").feature("ef2").selection().all();
    model.component("comp1").physics("cpt").feature("ef2").set("E_src", "root.comp1.ec.Ex");
    model.component("comp1").physics("cpt").feature("ef2").set("TimeDependenceOfField", "TimeHarmonic");

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

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(16, 19, 24, 27, 30, 33, 46);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurve", 0.15);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(4, 5, 6);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 35);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 15);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(1, 2, 3);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cpt", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std1").label("\u9759\u7535\u7814\u7a76");
    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/es", true);
    model.study("std2").feature("freq").setSolveFor("/physics/ec", true);
    model.study("std2").feature("freq").setSolveFor("/physics/cpt", true);
    model.study("std2").feature("freq").set("plist", "4[MHz]");
    model.study("std2").feature("freq").setSolveFor("/physics/es", false);
    model.study("std2").feature("freq").set("usesol", true);
    model.study("std2").feature("freq").set("notsolmethod", "sol");
    model.study("std2").feature("freq").set("notstudy", "std1");
    model.study("std2").label("\u7535\u6d41\u7814\u7a76");
    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/es", true);
    model.study("std3").feature("time").setSolveFor("/physics/ec", true);
    model.study("std3").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std3").feature("time").setSolveFor("/physics/es", false);
    model.study("std3").feature("time").setSolveFor("/physics/ec", false);
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std2");
    model.study("std3").feature("time").set("tlist", "range(0,2.5e-7,3.5e-5)");
    model.study("std3").label("\u7c92\u5b50\u8ffd\u8e2a\u7814\u7a76");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Dipole");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "es.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (ec)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "V2");
    model.result("pg3").feature("vol1").set("colortable", "Dipole");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u573a (ec)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("expr", "ec.normE");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Prism");
    model.result("pg4").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", 0.02);
    model.result("pg4").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg4").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("data", "parent");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg3").run();

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol3");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "part1");
    model.result("pg5").setIndex("looplevel", 141, 0);
    model.result("pg5").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg5").create("traj1", "ParticleTrajectories");
    model.result("pg5").feature("traj1").set("pointtype", "point");
    model.result("pg5").feature("traj1").set("linetype", "none");
    model.result("pg5").feature("traj1").create("col1", "Color");
    model.result("pg5").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "bottom");
    model.result("pg5").run();
    model.result("pg5").feature("traj1").set("linetype", "line");
    model.result("pg5").feature("traj1").set("pointtype", "none");
    model.result("pg5").feature("traj1").set("interpolation", "uniform");
    model.result("pg5").feature("traj1").set("interpcount", 1000);

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u79bb\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570");
    model.result("pg6").set("data", "part1");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").create("hist1", "Histogram");
    model.result("pg6").feature("hist1").set("markerpos", "datapoints");
    model.result("pg6").feature("hist1").set("linewidth", "preference");
    model.result("pg6").feature("hist1").set("expr", "cpt.Ep");
    model.result("pg6").feature("hist1").set("descr", "\u7c92\u5b50\u52a8\u80fd");
    model.result("pg6").feature("hist1").set("unit", "eV");
    model.result("pg6").feature("hist1").set("method", "limits");
    model.result("pg6").feature("hist1").set("limits", "range(0,0.1,10)");
    model.result("pg6").run();

    model.title("\u56db\u6781\u8d28\u8c31\u4eea");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u56db\u6781\u900f\u955c\u4e2d\u5404\u79cd\u5206\u5b50\u91cf\u7684\u79bb\u5b50\u8f68\u8ff9\uff0c\u5176\u4e2d\u5305\u542b\u76f4\u6d41\u7535\u573a\u548c\u4ea4\u6d41\u7535\u573a\u3002\n\n\u672c\u4f8b\u9700\u8981\u201c\u7c92\u5b50\u8ffd\u8e2a\u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("quadrupole_mass_spectrometer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
