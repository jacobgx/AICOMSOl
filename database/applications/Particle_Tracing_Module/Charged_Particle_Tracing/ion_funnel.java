/*
 * ion_funnel.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:50 by COMSOL 6.3.0.290. */
public class ion_funnel {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("cpt", "ChargedParticleTracing", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cpt", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "0.7[MHz]", "\u5c04\u9891\u9891\u7387");
    model.param().set("Vrf", "800[V]", "\u5c04\u9891\u7535\u538b\u5e45\u503c");
    model.param().set("EDC", "-3e3[V/m]", "\u9759\u6001\u8f74\u5411\u7535\u573a");
    model.param().set("ND", "3.2956e22[1/m^3]", "\u80cc\u666f\u6c14\u4f53\u6570\u5bc6\u5ea6");
    model.param().set("Nstraight", "15", "\u5782\u76f4\u6bb5\u7684\u7535\u6781\u6570");
    model.param().set("Ninclined", "21", "\u503e\u659c\u6bb5\u7684\u7535\u6781\u6570");
    model.param().set("Nelec", "Nstraight+Ninclined", "\u7535\u6781\u6570");
    model.param().set("welec", "20[mm]", "\u7535\u6781\u5bbd\u5ea6");
    model.param().set("telec", "1[mm]", "\u7535\u6781\u539a\u5ea6");
    model.param().set("tgap", "2.5[mm]", "\u7535\u6781\u95f4\u8ddd");
    model.param().set("hfunnel", "(Nelec-1)*tgap+Nelec*telec", "\u79bb\u5b50\u6f0f\u6597\u9ad8\u5ea6");
    model.param().set("rmin", "3[mm]", "\u6700\u5c0f\u5185\u534a\u5f84");
    model.param().set("rmax", "25[mm]", "\u6700\u5927\u5185\u534a\u5f84");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"welec", "telec"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"rmax", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"welec", "telec"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"rmax", "telec+tgap"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", "(Nstraight+1)/2");
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "2*(telec+tgap)"});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u51cf\u53bb\u7684\u57df");
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("arr2").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr2").set("linearsize", "(Nstraight-1)/2");
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"0", "2*(telec+tgap)"});
    model.component("comp1").geom("geom1").feature("arr2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr2").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").feature("arr2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"welec", "telec"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"rmin", "hfunnel-telec"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"welec", "telec"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"rmin+(rmax-rmin)/Ninclined", "0"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("pos", "hfunnel-(2*telec+tgap)", 1);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("arr3").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr3").set("linearsize", "(Ninclined+1)/2");
    model.component("comp1").geom("geom1").feature("arr3")
         .set("displ", new String[]{"2*(rmax-rmin)/Ninclined", "0"});
    model.component("comp1").geom("geom1").feature("arr3").setIndex("displ", "-2*(telec+tgap)", 1);
    model.component("comp1").geom("geom1").feature("arr3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr3").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").feature("arr3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").create("arr4", "Array");
    model.component("comp1").geom("geom1").feature("arr4").selection("input").set("r4");
    model.component("comp1").geom("geom1").feature("arr4").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr4").set("linearsize", "(Ninclined-1)/2");
    model.component("comp1").geom("geom1").feature("arr4")
         .set("displ", new String[]{"2*(rmax-rmin)/Ninclined", "0"});
    model.component("comp1").geom("geom1").feature("arr4").setIndex("displ", "-2*(telec+tgap)", 1);
    model.component("comp1").geom("geom1").feature("arr4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr4").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").feature("arr4").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"rmax+welec+10[mm]", "1"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("size", "hfunnel+20[mm]", 1);
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"0.2[mm]", "-10[mm]"});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r5");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("uni1", "Union");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("uni1").label("\u5947\u6570\u7535\u6781");
    model.component("comp1").selection("uni1").set("entitydim", 1);
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_arr1_bnd", "geom1_arr4_bnd"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u5076\u6570\u7535\u6781");
    model.component("comp1").selection("uni2").set("entitydim", 1);
    model.component("comp1").selection("uni2").set("input", new String[]{"geom1_arr2_bnd", "geom1_arr3_bnd"});
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("\u6240\u6709\u7535\u6781");
    model.component("comp1").selection("uni3").set("entitydim", 1);
    model.component("comp1").selection("uni3").set("input", new String[]{"uni1", "uni2"});
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(2, 3, 148);
    model.component("comp1").selection().create("uni4", "Union");
    model.component("comp1").selection("uni4").label("\u6240\u6709\u8fb9\u754c");
    model.component("comp1").selection("uni4").set("entitydim", 1);
    model.component("comp1").selection("uni4").set("input", new String[]{"uni3", "sel1"});

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

    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").selection().named("uni4");
    model.component("comp1").physics("es").feature("pot1").set("V0", "EDC*z");
    model.component("comp1").physics("ec").create("term1", "Terminal", 1);
    model.component("comp1").physics("ec").feature("term1").selection().named("uni1");
    model.component("comp1").physics("ec").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("ec").feature("term1").set("V0", "Vrf");
    model.component("comp1").physics("ec").create("term2", "Terminal", 1);
    model.component("comp1").physics("ec").feature("term2").selection().named("uni2");
    model.component("comp1").physics("ec").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("ec").feature("term2").set("V0", "-Vrf");
    model.component("comp1").physics("cpt").prop("IncludeOutOfPlane").setIndex("IncludeOutOfPlane", 1, 0);
    model.component("comp1").physics("cpt").feature("pp1").set("mp", "0.146[kg/mol]/N_A_const");
    model.component("comp1").physics("cpt").feature("pp1").set("Z", 1);
    model.component("comp1").physics("cpt").create("wall2", "Wall", 1);
    model.component("comp1").physics("cpt").feature("wall2").selection().set(1);
    model.component("comp1").physics("cpt").feature("wall2").set("WallCondition", "Bounce");
    model.component("comp1").physics("cpt").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("cpt").feature("relg1").setIndex("x0", "range(2,1,20)", 0);
    model.component("comp1").physics("cpt").feature("relg1").set("VelocitySpecification", "Maxwellian");
    model.component("comp1").physics("cpt").feature("relg1").setIndex("M", 5, 0);
    model.component("comp1").physics("cpt").create("ef1", "ElectricForce", 2);
    model.component("comp1").physics("cpt").feature("ef1").selection().all();
    model.component("comp1").physics("cpt").feature("ef1").set("E_src", "root.comp1.es.Er");
    model.component("comp1").physics("cpt").feature("ef1").set("UsePPR", true);
    model.component("comp1").physics("cpt").create("ef2", "ElectricForce", 2);
    model.component("comp1").physics("cpt").feature("ef2").selection().all();
    model.component("comp1").physics("cpt").feature("ef2").set("E_src", "root.comp1.ec.Er");
    model.component("comp1").physics("cpt").feature("ef2").set("TimeDependenceOfField", "TimeHarmonic");
    model.component("comp1").physics("cpt").feature("ef2").set("UsePPR", true);

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "Qm");
    model.component("comp1").func("an1").set("expr", "1.15e-18*x^(-0.1)*(1+0.015/x)^0.6");
    model.component("comp1").func("an1").setIndex("argunit", "eV", 0);
    model.component("comp1").func("an1").set("fununit", "m^2");
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").set("funcname", "Qi");
    model.component("comp1").func("an2").set("expr", "2e-19/(x^(0.5)*(1+x))+3e-19*x/(1+x/3)^(2.3)");
    model.component("comp1").func("an2").setIndex("argunit", "eV", 0);
    model.component("comp1").func("an2").set("fununit", "m^2");

    model.component("comp1").physics("cpt").create("col1", "Collisions", 2);
    model.component("comp1").physics("cpt").feature("col1").selection().all();
    model.component("comp1").physics("cpt").feature("col1").set("Nd", "ND");
    model.component("comp1").physics("cpt").feature("col1").set("CountAllCollisions", true);
    model.component("comp1").physics("cpt").feature("col1").create("ela1", "Elastic", 2);
    model.component("comp1").physics("cpt").feature("col1").feature("ela1").set("xsec", "Qi(cpt.Ep)");
    model.component("comp1").physics("cpt").feature("col1").feature("ela1").set("CountCollisions", true);
    model.component("comp1").physics("cpt").feature("col1").create("cex1", "ResonantChargeExchange", 2);
    model.component("comp1").physics("cpt").feature("col1").feature("cex1").set("xsec", "(Qm(cpt.Ep)-Qi(cpt.Ep))/2");
    model.component("comp1").physics("cpt").feature("col1").feature("cex1").set("CountCollisions", true);

    model.study("std1").feature("stat").setSolveFor("/physics/ec", false);
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").feature("freq").setSolveFor("/physics/es", false);
    model.study("std1").feature("freq").set("usesol", true);
    model.study("std1").feature("freq").set("notsolmethod", "sol");
    model.study("std1").feature("freq").set("notstudy", "std1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (es)");
    model.result("pg1").set("dataisaxisym", "off");
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
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf\uff0c\u56de\u8f6c\u51e0\u4f55 (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Dipole");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "es.normE");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
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
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u52bf (ec)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "V2");
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("str1", "Streamline");
    model.result("pg4").feature("str1").set("showsolutionparams", "on");
    model.result("pg4").feature("str1").set("solutionparams", "parent");
    model.result("pg4").feature("str1").set("expr", new String[]{"ec.Er", "ec.Ez"});
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
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148);
    model.result("pg4").feature("str1").set("inheritplot", "surf1");
    model.result("pg4").feature("str1").feature().create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "V2");
    model.result("pg4").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg4").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("str1").feature().create("filt1", "Filter");
    model.result("pg4").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("modenumber", "comp1.ec.m");
    model.result().dataset("rev2").set("data", "dset1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u52bf\uff0c\u56de\u8f6c\u51e0\u4f55 (ec)");
    model.result("pg5").set("data", "rev2");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "V2");
    model.result("pg5").feature("vol1").set("colortable", "Dipole");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7535\u573a (ec)");
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "ec.normE");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature().create("str1", "Streamline");
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("solutionparams", "parent");
    model.result("pg6").feature("str1").set("expr", new String[]{"ec.Er", "ec.Ez"});
    model.result("pg6").feature("str1").set("titletype", "none");
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("udist", 0.02);
    model.result("pg6").feature("str1").set("maxlen", 0.4);
    model.result("pg6").feature("str1").set("maxsteps", 5000);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("inheritcolor", false);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("data", "parent");
    model.result("pg6").feature("str1").selection().geom("geom1", 1);
    model.result("pg6").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148);
    model.result("pg6").feature("str1").set("inheritplot", "surf1");
    model.result("pg6").feature("str1").feature().create("col1", "Color");
    model.result("pg6").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg6").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg6").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg6").feature("str1").feature().create("filt1", "Filter");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u4ea4\u6d41\u7535\u4f4d\u4e0e\u76f4\u6d41\u7535\u4f4d\u4e4b\u548c");
    model.result("pg7").create("con1", "Contour");
    model.result("pg7").feature("con1").set("expr", "V+V2");
    model.result("pg7").feature("con1").set("number", 30);
    model.result("pg7").feature("con1").set("contourtype", "filled");
    model.result("pg7").feature("con1").set("colorlegend", false);
    model.result("pg7").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/es", false);
    model.study("std2").feature("time").setSolveFor("/physics/ec", false);
    model.study("std2").feature("time").setSolveFor("/physics/cpt", true);
    model.study("std2").feature("time").set("tlist", "range(0,1.0e-5,0.0015)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1e-3");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol3").feature("t1").set("atolglobal", "1e-4");
    model.sol("sol3").feature("t1").set("maxstepconstraintgenalpha", "const");
    model.sol("sol3").feature("t1").set("maxstepgenalpha", "1e-8");
    model.sol("sol3").feature("t1").set("reacf", false);
    model.sol("sol3").feature("t1").set("storeudot", false);
    model.sol("sol3").feature("t1").feature("aDef").set("convinfo", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol3");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qr", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_cpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "cpt");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "part1");
    model.result("pg8").setIndex("looplevel", 151, 0);
    model.result("pg8").label("\u7c92\u5b50\u8f68\u8ff9 (cpt)");
    model.result("pg8").create("traj1", "ParticleTrajectories");
    model.result("pg8").feature("traj1").set("pointtype", "point");
    model.result("pg8").feature("traj1").set("linetype", "none");
    model.result("pg8").feature("traj1").create("col1", "Color");
    model.result("pg8").feature("traj1").feature("col1").set("expr", "cpt.V");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("traj1").set("linetype", "line");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u6a2a\u5411\u7c92\u5b50\u4f4d\u7f6e");
    model.result("pg9").set("data", "part1");
    model.result("pg9").setIndex("looplevel", 151, 0);
    model.result("pg9").set("edges", false);
    model.result("pg9").create("phpo1", "PhasePortrait");
    model.result("pg9").feature("phpo1").set("xdata", "manual");
    model.result("pg9").feature("phpo1").set("xmanual", "qr*cos(qphi)");
    model.result("pg9").feature("phpo1").set("ydata", "manual");
    model.result("pg9").feature("phpo1").set("ymanual", "qr*sin(qphi)");
    model.result("pg9").feature("phpo1").create("col1", "Color");
    model.result("pg9").run();
    model.result("pg9").feature("phpo1").feature("col1").set("expr", "qz>120[mm]");
    model.result("pg9").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg9");
    model.result().export("anim1").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u5e73\u5747\u5f84\u5411\u7c92\u5b50\u4f4d\u7f6e");
    model.result("pg10").set("data", "part1");
    model.result("pg10").create("ptp1", "Particle1D");
    model.result("pg10").feature("ptp1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptp1").set("linewidth", "preference");
    model.result("pg10").feature("ptp1").set("expr", "qr");
    model.result("pg10").feature("ptp1").set("descr", "\u7c92\u5b50\u4f4d\u7f6e\uff0cr \u5206\u91cf");
    model.result("pg10").feature("ptp1").set("dataseries", "average");
    model.result("pg10").feature("ptp1").create("col1", "Color");
    model.result("pg10").run();
    model.result("pg10").feature("ptp1").feature("col1").set("expr", "cpt.Ftr");
    model.result("pg10").feature("ptp1").feature("col1").set("descr", "\u603b\u529b\uff0cr \u5206\u91cf");
    model.result("pg10").run();

    model.title("\u79bb\u5b50\u6f0f\u6597");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u7535\u52a8\u79bb\u5b50\u6f0f\u6597\u7684\u805a\u7126\u6548\u5e94\u3002\u7531\u4e8e\u53ef\u5728\u9ad8\u80cc\u666f\u6c14\u538b\u4e0b\u5de5\u4f5c\uff0c\u79bb\u5b50\u6f0f\u6597\u7ecf\u5e38\u7528\u6765\u8026\u5408\u8bbe\u5907\uff0c\u4f8b\u5982\u79bb\u5b50\u8fc1\u79fb\u5149\u8c31\u4eea\u548c\u8d28\u8c31\u4eea\u7b49\uff0c\u4ee5\u63d0\u9ad8\u5b83\u4eec\u7684\u7075\u654f\u5ea6\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u78b0\u649e\u201d\u7279\u5f81\u4e3a\u79bb\u5b50\u4e0e\u4e2d\u6027\u80cc\u666f\u6c14\u4f53\u7684\u76f8\u4e92\u4f5c\u7528\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("ion_funnel.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
