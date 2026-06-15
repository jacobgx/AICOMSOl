/*
 * reflections_water_sediment.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class reflections_water_sediment {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Underwater_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("pelw", "PoroelasticWavesSinglePhysics", "geom1");

    model.component("comp1").multiphysics().create("apb1", "AcousticPorousBoundary", 1);
    model.component("comp1").multiphysics("apb1").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("apb1").set("Porous_physics", "pelw");
    model.component("comp1").multiphysics("apb1").selection().all();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/pelw", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/apb1", true);

    model.param().label("\u53c2\u6570 - \u6750\u6599");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("c0", "1414[m/s]", "\u6c34\u4e2d\u7684\u58f0\u901f");
    model.param()
         .set("cs_poro", "130[m/s]", "\u591a\u5b54\u5f39\u6027\u6ce2\u57df\u4e2d\u6162\u526a\u5207\u6ce2\u7684\u901f\u5ea6");
    model.param().set("rhoF", "1000[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("Kf", "rhoF*c0^2", "\u6d41\u4f53\u7684\u4f53\u79ef\u6a21\u91cf");
    model.param().set("muF", "1e-3[Pa*s]", "\u6d41\u4f53\u9ecf\u5ea6");
    model.param().set("epsilonP", "0.47", "\u5b54\u9699\u7387");
    model.param().set("a", "4e-3[cm]", "\u5b54\u9699\u5927\u5c0f\u53c2\u6570");
    model.param().set("tau0", "1.25", "\u66f2\u6298");
    model.param().set("kappaP", "1e-6[cm^2]", "\u6e17\u900f\u7387");
    model.param().set("Kb", "4.36e7[Pa]", "\u6846\u67b6\u7684\u4f53\u79ef\u6a21\u91cf");
    model.param().set("G", "2.61e7[Pa]", "\u6846\u67b6\u7684\u526a\u5207\u6a21\u91cf");
    model.param().set("Ks", "3.6e10[Pa]", "\u56fa\u4f53\u9897\u7c92\u7684\u4f53\u79ef\u6a21\u91cf");
    model.param().set("rhos", "2650[kg/m^3]", "\u56fa\u4f53\u5bc6\u5ea6");
    model.param().set("rhod", "rhos*(1-epsilonP)", "\u6392\u6c34\u5bc6\u5ea6");
    model.param().set("alphaB0", "1-Kb/Ks", "Biot-Willis \u7cfb\u6570");
    model.param().set("logdec", "0.15", "\u5bf9\u6570\u8870\u51cf\u56e0\u5b50");
    model.param().set("Kbc", "Kb*(1+i*logdec/pi)", "\u6846\u67b6\u7684\u590d\u4f53\u79ef\u6a21\u91cf");
    model.param().set("Gc", "G*(1+i*logdec/pi)", "\u6846\u67b6\u7684\u590d\u526a\u5207\u6a21\u91cf");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 - \u6a21\u578b");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("f0", "1000[Hz]", "\u9a71\u52a8\u9891\u7387");
    model.param("par2").set("theta0", "0[deg]", "\u5165\u5c04\u89d2");
    model.param("par2").set("lam0", "c0/f0", "f0 \u7684\u6ce2\u957f");
    model.param("par2").set("W", "lam0", "\u57df\u5bbd\u5ea6");
    model.param("par2").set("H", "2*lam0", "\u57df\u9ad8\u5ea6");
    model.param("par2").set("Hpml", "lam0/3", "PML \u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H"});
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Hpml", 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"W", "H"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-H"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "Hpml", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("k0", "intop1(acpr.k)", "\u5165\u5c04\u573a\u7684\u6ce2\u6570");
    model.component("comp1").variable("var1").set("kx_e", "sin(theta0)", "\u5e73\u9762\u6ce2\u65b9\u5411\uff0cx");
    model.component("comp1").variable("var1").set("ky_e", "-cos(theta0)", "\u5e73\u9762\u6ce2\u65b9\u5411\uff0cy");
    model.component("comp1").variable("var1")
         .set("k0x", "k0*kx_e", "\u5165\u5c04\u573a\u7684\u6ce2\u77e2 x \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("k0y", "k0*ky_e", "\u5165\u5c04\u573a\u7684\u6ce2\u77e2 y \u5206\u91cf");
    model.component("comp1").variable("var1").set("p_in", "acpr.p_b", "\u5165\u5c04\u538b\u529b\u573a");
    model.component("comp1").variable("var1").set("p_re", "acpr.p_s", "\u53cd\u5c04\u538b\u529b\u573a");
    model.component("comp1").variable("var1").set("R", "p_re/p_in", "\u53cd\u5c04\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("absR", "abs(aveop1(R))", "\u53cd\u5c04\u7cfb\u6570\u7684\u7edd\u5bf9\u503c\u548c\u5e73\u5747\u503c");
    model.component("comp1").variable("var1")
         .set("alpha_R", "1-absR^2", "\u5438\u58f0\u7cfb\u6570\uff08\u57fa\u4e8e R\uff09");
    model.component("comp1").variable("var1").set("Pin", "intop2(-acpr.I_by)", "\u5165\u5c04\u529f\u7387");
    model.component("comp1").variable("var1").set("Pre", "intop2(acpr.I_sy)", "\u53cd\u5c04\u529f\u7387");
    model.component("comp1").variable("var1").set("alpha", "1-Pre/Pin", "\u5438\u6536\u7cfb\u6570");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(6);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(6);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u591a\u5b54\u57df");
    model.component("comp1").selection("sel1").set(1, 2);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u6c34\u57df");
    model.component("comp1").selection("sel2").set(3, 4);

    model.component("comp1").physics("pelw").selection().named("sel1");
    model.component("comp1").physics("pelw").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("pelw").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("pelw").feature("pc1").selection().set(1, 3, 10, 11);
    model.component("comp1").physics("pelw").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("pelw").feature("pc1").set("kFloquet", new String[]{"k0x", "k0y", "0"});
    model.component("comp1").physics("acpr").selection().named("sel2");
    model.component("comp1").physics("acpr").prop("ReferencePressure")
         .set("ReferenceType", "ReferencePressureWater");
    model.component("comp1").physics("acpr").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("acpr").feature("pc1").selection().set(5, 7, 12, 13);
    model.component("comp1").physics("acpr").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("acpr").feature("pc1").set("kFloquet", new String[]{"k0x", "k0y", "0"});
    model.component("comp1").physics("acpr").create("bpf1", "BackgroundPressureField", 2);
    model.component("comp1").physics("acpr").feature("bpf1").selection().named("sel2");
    model.component("comp1").physics("acpr").feature("bpf1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf1").set("dir", new String[]{"kx_e", "ky_e", "0"});
    model.component("comp1").physics("acpr").feature("bpf1").set("CalculateIntensity", true);
    model.component("comp1").physics("acpr").feature("bpf1").set("rho_mat", "from_mat");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6c34");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6c89\u79ef\u7269");

    model.component("comp1").physics("pelw").feature("pm1").set("PoroelasticModel", "Biot");
    model.component("comp1").physics("pelw").feature("pm1").set("SolidMaterial", "mat2");
    model.component("comp1").physics("pelw").feature("pm1").set("ViscosityModel", "BiotsHighFrequencyRange");
    model.component("comp1").physics("pelw").feature("pm1").set("referenceFrequencyOption", "poreSize");
    model.component("comp1").physics("pelw").feature("pm1").set("a", "a");

    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhoF"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"c0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"muF"});
    model.component("comp1").material("mat1").propertyGroup("def").set("compressibility", new String[]{"1/Kf"});
    model.component("comp1").material("mat2").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat2").propertyGroup("KG").set("K", new String[]{"Kbc"});
    model.component("comp1").material("mat2").propertyGroup("KG").set("G", new String[]{"Gc"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rhod"});
    model.component("comp1").material("mat2").propertyGroup("def").set("porosity", new String[]{"epsilonP"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappaP"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("PoroelasticModel", "PoroelasticModel", "Poroelastic_material");
    model.component("comp1").material("mat2").propertyGroup("PoroelasticModel")
         .set("alphaB", new String[]{"alphaB0"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("PoroacousticsModel", "PoroacousticsModel", "Poroacoustics_model");
    model.component("comp1").material("mat2").propertyGroup("PoroacousticsModel").set("tau", new String[]{"tau0"});

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1);
    model.component("comp1").coordSystem("pml1").set("wavelengthSource", "pelw");
    model.component("comp1").coordSystem("pml1").set("PMLfactor", "if(theta0==90[deg],1e6,1/cos(theta0))");
    model.component("comp1").coordSystem().create("pml2", "PML");
    model.component("comp1").coordSystem("pml2").selection().set(4);
    model.component("comp1").coordSystem("pml2").set("stretchingType", "rational");
    model.component("comp1").coordSystem("pml2").set("PMLfactor", "if(theta0==90[deg],1e6,1/cos(theta0))");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/5");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "lam0/10");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "cs_poro/f0/5");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmin", "cs_poro/f0/10");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 30);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u8be6\u7ec6\u7684\u89d2\u5ea6\u626b\u63cf");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").feature("freq").set("useparam", true);
    model.study("std1").feature("freq").set("sweeptype", "filled");
    model.study("std1").feature("freq").setIndex("pname_aux", "a", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "m", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "a", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "m", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "theta0", 0);
    model.study("std1").feature("freq")
         .setIndex("plistarr_aux", "range(0,10[deg],50[deg]) range(51[deg],1[deg],89[deg]) 89.5[deg]", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "deg", 0);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "a", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "a", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "f0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10 100 1000 10000", 0);
    model.study("std1").feature("param").setIndex("punit", "Hz", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(2, 3);
    model.result().dataset().create("arr1", "Array2D");
    model.result().dataset("arr1").set("data", "dset2");
    model.result().dataset("arr1").set("fullsize", new int[]{4, 1});
    model.result().dataset("arr1").set("floquetper", true);
    model.result().dataset("arr1").set("wavevector", new String[]{"k0x", "k0y"});
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u5165\u5c04\u538b\u529b");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u5165\u5c04\u538b\u529b");
    model.result("pg1").set("paramindicator", "f0 = eval(freq) Hz, theta0 = eval(theta0, deg) deg");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "acpr.p_b");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"k0x", "k0y"});
    model.result("pg1").feature("arws1").set("xnumber", 5);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("solnum", 4);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u53cd\u5c04\u538b\u529b");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("solnum", 4);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u53cd\u5c04\u538b\u529b");
    model.result("pg2").set("paramindicator", "f0 = eval(freq) Hz, theta0 = eval(theta0, deg) deg");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "acpr.p_s");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "pelw.p_s");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u603b\u538b");
    model.result("pg3").set("data", "arr1");
    model.result("pg3").set("solnum", 4);
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u603b\u538b");
    model.result("pg3").set("paramindicator", "f0 = eval(freq) Hz, theta0 = eval(theta0, deg) deg");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "apb1.p_t");
    model.result("pg3").feature("surf1").set("descr", "\u603b\u58f0\u538b");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u4f4d\u79fb");
    model.result("pg4").set("paramindicator", "f0 = eval(freq) Hz, theta0 = eval(theta0, deg) deg");
    model.result("pg4").set("data", "arr1");
    model.result("pg4").set("solnum", 4);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "pelw.disp");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("expr", new String[]{"u", "v"});
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", "2e9");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").set("outersolnum", 1);
    model.result("pg3").set("solnum", 26);
    model.result("pg3").run();
    model.result("pg3").set("outersolnum", 2);
    model.result("pg3").run();
    model.result("pg3").set("outersolnum", 3);
    model.result("pg3").run();
    model.result("pg3").set("outersolnum", 4);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").set("outersolnum", 1);
    model.result("pg4").set("solnum", 26);
    model.result("pg4").run();
    model.result("pg4").set("outersolnum", 2);
    model.result("pg4").run();
    model.result("pg4").set("outersolnum", 3);
    model.result("pg4").run();
    model.result("pg4").set("outersolnum", 4);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u6ce2\u901f");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "acpr.c");
    model.result("pg5").feature("surf1").set("descr", "\u58f0\u901f");
    model.result("pg5").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("expr", "pelw.cp_fast");
    model.result("pg5").feature("surf2").set("titletype", "none");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature("surf2").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("filt1").set("expr", "(y>-H/3)");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf3", "surf2");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").set("expr", "pelw.cp_slow");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").feature("filt1").set("expr", "(y<-H/3)*(y>-2*H/3)");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf4", "surf2");
    model.result("pg5").run();
    model.result("pg5").feature("surf4").set("expr", "pelw.cs_poro");
    model.result("pg5").run();
    model.result("pg5").feature("surf4").feature("filt1").set("expr", "(y<-2*H/3)");
    model.result("pg5").run();
    model.result("pg5").create("ann1", "Annotation");
    model.result("pg5").feature("ann1").set("text", "\u58f0\u901f = eval(acpr.c) m/s");
    model.result("pg5").feature("ann1").set("posxexpr", "W");
    model.result("pg5").feature("ann1").set("posyexpr", "H/2");
    model.result("pg5").run();
    model.result("pg5").create("ann2", "Annotation");
    model.result("pg5").feature("ann2")
         .set("text", "\u5feb\u901f\u538b\u529b\u6ce2\u7684\u901f\u5ea6 = eval(real(pelw.cp_fast)) m/s");
    model.result("pg5").feature("ann2").set("posxexpr", "W");
    model.result("pg5").feature("ann2").set("posyexpr", "-H/6");
    model.result("pg5").run();
    model.result("pg5").create("ann3", "Annotation");
    model.result("pg5").feature("ann3")
         .set("text", "\u7f13\u6162\u538b\u529b\u6ce2\u7684\u901f\u5ea6 = eval(real(pelw.cp_slow)) m/s");
    model.result("pg5").feature("ann3").set("posxexpr", "W");
    model.result("pg5").feature("ann3").set("posyexpr", "-3*H/6");
    model.result("pg5").run();
    model.result("pg5").create("ann4", "Annotation");
    model.result("pg5").feature("ann4").set("text", "\u526a\u5207\u6ce2\u901f = eval(real(pelw.cs_poro)) m/s");
    model.result("pg5").feature("ann4").set("posxexpr", "W");
    model.result("pg5").feature("ann4").set("posyexpr", "-5*H/6");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u53cd\u5c04\u7cfb\u6570 vs. \u5165\u5c04\u89d2");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u53cd\u5c04\u7cfb\u6570\uff1a|R|");
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", -2);
    model.result("pg6").set("xmax", 92);
    model.result("pg6").set("ymin", -0.05);
    model.result("pg6").set("ymax", 1.05);
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "absR", 0);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "theta0");
    model.result("pg6").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg6").feature("glob1").set("autodescr", false);
    model.result("pg6").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").setSolveFor("/physics/pelw", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/apb1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u8be6\u7ec6\u7684\u9891\u7387\u626b\u63cf");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").feature("freq").set("useparam", true);
    model.study("std2").feature("freq").set("sweeptype", "filled");
    model.study("std2").feature("freq").setIndex("pname_aux", "a", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "m", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "a", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "m", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "theta0", 0);
    model.study("std2").feature("freq")
         .setIndex("plistarr_aux", "30[deg] 40[deg] 50[deg] 60[deg] 70[deg] 80[deg]", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "deg", 0);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "a", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "a", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "f0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "10^{range(1,4/14,5)}", 0);
    model.study("std2").feature("param").setIndex("punit", "Hz", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std2");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol8");
    model.batch("p2").run("compute");

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u53cd\u5c04\u7cfb\u6570 vs. \u9a71\u52a8\u9891\u7387");
    model.result("pg7").set("data", "none");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 8.5);
    model.result("pg7").set("xmax", 117171);
    model.result("pg7").set("ymin", -0.05);
    model.result("pg7").set("ymax", 1.05);
    model.result("pg7").set("xlog", true);
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "lowerleft");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").set("data", "dset4");
    model.result("pg7").feature("glob1").set("innerinput", "manualindices");
    model.result("pg7").feature("glob1").set("solnumindices", 1);
    model.result("pg7").feature("glob1").setIndex("expr", "absR", 0);
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "f0");
    model.result("pg7").feature("glob1").set("legendmethod", "manual");
    model.result("pg7").feature("glob1").setIndex("legends", "theta0 = 30[deg]", 0);
    model.result("pg7").feature().duplicate("glob2", "glob1");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").set("solnumindices", 2);
    model.result("pg7").feature("glob2").setIndex("legends", "theta0 = 40[deg]", 0);
    model.result("pg7").feature().duplicate("glob3", "glob2");
    model.result("pg7").run();
    model.result("pg7").feature("glob3").set("solnumindices", 3);
    model.result("pg7").feature("glob3").setIndex("legends", "theta0 = 50[deg]", 0);
    model.result("pg7").feature().duplicate("glob4", "glob3");
    model.result("pg7").run();
    model.result("pg7").feature("glob4").set("solnumindices", 4);
    model.result("pg7").feature("glob4").setIndex("legends", "theta0 = 60[deg]", 0);
    model.result("pg7").feature().duplicate("glob5", "glob4");
    model.result("pg7").run();
    model.result("pg7").feature("glob5").set("solnumindices", 5);
    model.result("pg7").feature("glob5").setIndex("legends", "theta0 = 70[deg]", 0);
    model.result("pg7").feature().duplicate("glob6", "glob5");
    model.result("pg7").run();
    model.result("pg7").feature("glob6").set("solnumindices", 6);
    model.result("pg7").feature("glob6").setIndex("legends", "theta0 = 80[deg]", 0);
    model.result("pg7").run();
    model.result("pg6").run();
    model.result().duplicate("pg8", "pg6");
    model.result("pg8").run();
    model.result("pg8").label("\u5438\u58f0\u7cfb\u6570 vs. \u5165\u5c04\u89d2");
    model.result("pg8").set("ylabel", "\u5438\u58f0\u7cfb\u6570\uff1a\\alpha");
    model.result("pg8").set("legendpos", "lowerleft");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "alpha", 0);
    model.result("pg8").run();
    model.result("pg3").run();

    model.title("\u6c34-\u6c89\u79ef\u7269\u754c\u9762\u7684\u58f0\u53cd\u5c04");

    model
         .description("\u672c\u4f8b\u53ef\u4ee5\u786e\u5b9a\u5728\u4e0d\u540c\u9891\u7387\u548c\u5165\u5c04\u89d2\u6761\u4ef6\u4e0b\uff0c\u5e73\u9762\u58f0\u6ce2\u5728\u6c34-\u6c89\u79ef\u7269\u754c\u9762\u7684\u53cd\u5c04\u7cfb\u6570\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u58f0-\u591a\u5b54\u5f39\u6027\u6ce2\u76f8\u4e92\u4f5c\u7528\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\u5bf9\u4efb\u610f\u591a\u5b54\u7269\u8d28\u4e2d\u58f0\u6ce2\u4e0e\u5f39\u6027\u6ce2\u7684\u8026\u5408\u73b0\u8c61\u8fdb\u884c\u5efa\u6a21\uff0c\u4ee5\u63cf\u8ff0\u6c34-\u6c89\u79ef\u7269\u7cfb\u7edf\u7684\u884c\u4e3a\u3002\u6a21\u578b\u7ed3\u679c\u4e0e\u540c\u884c\u8bc4\u5ba1\u7814\u7a76\u8bba\u6587\u4e2d\u83b7\u5f97\u7684\u7ed3\u679c\u9ad8\u5ea6\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    return model;
  }

  public static Model run2(Model model) {
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
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();

    model.label("reflections_water_sediment.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
