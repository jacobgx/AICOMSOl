/*
 * acoustic_cloaking.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class acoustic_cloaking {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.component("comp1").geom("geom1").insertFile("acoustic_cloaking_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("boxsel10");

    model.param().set("rhob", "1.25[kg/m^3]");
    model.param().descr("rhob", "\u80cc\u666f\u6750\u6599\u7684\u5bc6\u5ea6");
    model.param().set("cb", "343[m/s]");
    model.param().descr("cb", "\u80cc\u666f\u6750\u6599\u7684\u58f0\u901f");
    model.param().set("f0", "300[Hz]");
    model.param().descr("f0", "\u5206\u6790\u9891\u7387");
    model.param().set("lam0", "cb/f0");
    model.param().descr("lam0", "\u6ce2\u957f");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");
    model.component("comp1").coordSystem("sys2").setIndex("origin", "x1", 0);
    model.component("comp1").coordSystem("sys2").setIndex("origin", "y1", 1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u5f84\u5411\u5750\u6807\uff1a\u5747\u5300\u5316\u6597\u7bf7");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("geom1_sel3");
    model.component("comp1").variable("var1").set("r", "sys2.r");
    model.component("comp1").variable("var1").descr("r", "\u5f84\u5411\u5750\u6807");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u5f84\u5411\u5750\u6807\uff1a50 \u5c42\u6597\u7bf7");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().named("geom1_boxsel1");
    model.component("comp1").variable("var2").set("r", "sqrt((x-x3)^2+(y-y3)^2)", "\u5f84\u5411\u5750\u6807");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u5f84\u5411\u5750\u6807\uff1a20 \u5c42\u6597\u7bf7");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().named("geom1_boxsel2");
    model.component("comp1").variable("var3").set("r", "sqrt((x-x4)^2+(y-y4)^2)", "\u5f84\u5411\u5750\u6807");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u53d8\u91cf\uff1a\u58f0\u5b66\u9690\u5f62\u6570\u636e");
    model.component("comp1").variable("var4").selection().geom("geom1", 2);
    model.component("comp1").variable("var4").selection().named("geom1_unisel1");
    model.component("comp1").variable("var4").set("rho1", "rhob*(r+sqrt(2*r*R1-R1^2))/(r-R1)");
    model.component("comp1").variable("var4").descr("rho1", "\u5bc6\u5ea6\uff0c\u6750\u6599 1");
    model.component("comp1").variable("var4").set("c1", "cb*(R2-R1)/R2*r/(r-R1)");
    model.component("comp1").variable("var4").descr("c1", "\u58f0\u901f\uff0c\u6750\u6599 1");
    model.component("comp1").variable("var4").set("rho2", "rhob^2/rho1");
    model.component("comp1").variable("var4").descr("rho2", "\u5bc6\u5ea6\uff0c\u6750\u6599 2");
    model.component("comp1").variable("var4").set("c2", "c1");
    model.component("comp1").variable("var4").descr("c2", "\u58f0\u901f\uff0c\u6750\u6599 2");
    model.component("comp1").variable("var4").set("K1", "rho1*c1^2");
    model.component("comp1").variable("var4").descr("K1", "\u672c\u4f53\u6a21\u91cf\uff0c\u6750\u6599 1");
    model.component("comp1").variable("var4").set("K2", "rho2*c2^2");
    model.component("comp1").variable("var4").descr("K2", "\u672c\u4f53\u6a21\u91cf\uff0c\u6750\u6599 2");
    model.component("comp1").variable("var4").set("K", "2*K1*K2/(K1+K2)");
    model.component("comp1").variable("var4").descr("K", "\u7b49\u6548\u672c\u4f53\u6a21\u91cf");
    model.component("comp1").variable("var4").set("rho_tangential", "2*rho1*rho2/(rho1+rho2)");
    model.component("comp1").variable("var4").descr("rho_tangential", "\u6cbf\u5c42\u7684\u5bc6\u5ea6");
    model.component("comp1").variable("var4").set("rho_normal", "(rho1+rho2)/2");
    model.component("comp1").variable("var4").descr("rho_normal", "\u5782\u76f4\u4e8e\u5c42\u7684\u5bc6\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhob"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", new String[]{"cb"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6750\u6599 1");
    model.component("comp1").material("mat2").selection().named("geom1_sel1");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", new String[]{"c1"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u6750\u6599 2");
    model.component("comp1").material("mat3").selection().named("geom1_sel2");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"rho2"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", new String[]{"c2"});
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("\u5747\u5300\u5316\u6750\u6599");
    model.component("comp1").material("mat4").selection().named("geom1_sel3");
    model.component("comp1").material("mat4").propertyGroup()
         .create("AnisotropicAcousticsModel", "AnisotropicAcousticsModel", "Anisotropic_acoustics_model");
    model.component("comp1").material("mat4").propertyGroup("AnisotropicAcousticsModel")
         .set("rho_eff", new String[]{"rho_normal", "rho_tangential", "rho_tangential"});
    model.component("comp1").material("mat4").propertyGroup("AnisotropicAcousticsModel")
         .set("K_eff", new String[]{"K"});

    model.nodeGroup().create("grp1", "Physics", "acpr");
    model.nodeGroup("grp1").label("\u5747\u5300\u5316\u6a21\u578b");

    model.component("comp1").physics("acpr").create("cwr1", "CylindricalWaveRadiation", 1);

    model.nodeGroup("grp1").add("cwr1");

    model.component("comp1").physics("acpr").feature("cwr1").selection().set(154);
    model.component("comp1").physics("acpr").feature("cwr1").set("r0", new String[]{"x1", "y1", "0"});
    model.component("comp1").physics("acpr").create("bpf1", "BackgroundPressureField", 2);

    model.nodeGroup("grp1").add("bpf1");

    model.component("comp1").physics("acpr").feature("bpf1").selection().named("geom1_boxsel7");
    model.component("comp1").physics("acpr").feature("bpf1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf1").set("PressureFieldMaterial", "mat1");
    model.component("comp1").physics("acpr").feature("bpf1").set("phi", "acpr.bpf1.k*x1");
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 1);

    model.nodeGroup("grp1").add("sym1");

    model.component("comp1").physics("acpr").feature("sym1").selection().named("geom1_boxsel3");
    model.component("comp1").physics("acpr").create("aam1", "AnisotropicAcousticsModel", 2);

    model.nodeGroup("grp1").add("aam1");

    model.component("comp1").physics("acpr").feature("aam1").selection().set(4);
    model.component("comp1").physics("acpr").feature("aam1").set("coordinateSystem", "sys2");

    model.nodeGroup().create("grp2", "Physics", "acpr");
    model.nodeGroup("grp2").label("\u65e0\u6597\u7bf7\u6a21\u578b");

    model.component("comp1").physics("acpr").create("cwr2", "CylindricalWaveRadiation", 1);

    model.nodeGroup("grp2").add("cwr2");

    model.component("comp1").physics("acpr").feature("cwr2").selection().set(209);
    model.component("comp1").physics("acpr").feature("cwr2").set("r0", new String[]{"x2", "y2", "0"});
    model.component("comp1").physics("acpr").create("bpf2", "BackgroundPressureField", 2);

    model.nodeGroup("grp2").add("bpf2");

    model.component("comp1").physics("acpr").feature("bpf2").selection().named("geom1_boxsel8");
    model.component("comp1").physics("acpr").feature("bpf2").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf2").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf2").set("phi", "acpr.bpf2.k*x2");
    model.component("comp1").physics("acpr").create("sym2", "Symmetry", 1);

    model.nodeGroup("grp2").add("sym2");

    model.component("comp1").physics("acpr").feature("sym2").selection().named("geom1_boxsel4");

    model.nodeGroup().create("grp3", "Physics", "acpr");
    model.nodeGroup("grp3").label("50 \u5c42\u6597\u7bf7\u6a21\u578b");

    model.component("comp1").physics("acpr").create("cwr3", "CylindricalWaveRadiation", 1);

    model.nodeGroup("grp3").add("cwr3");

    model.component("comp1").physics("acpr").feature("cwr3").selection().set(153);
    model.component("comp1").physics("acpr").feature("cwr3").set("r0", new String[]{"x3", "y3", "0"});
    model.component("comp1").physics("acpr").create("bpf3", "BackgroundPressureField", 2);

    model.nodeGroup("grp3").add("bpf3");

    model.component("comp1").physics("acpr").feature("bpf3").selection().named("geom1_boxsel9");
    model.component("comp1").physics("acpr").feature("bpf3").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf3").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf3").set("phi", "acpr.bpf3.k*x3");
    model.component("comp1").physics("acpr").create("sym3", "Symmetry", 1);

    model.nodeGroup("grp3").add("sym3");

    model.component("comp1").physics("acpr").feature("sym3").selection().named("geom1_boxsel5");

    model.nodeGroup().create("grp4", "Physics", "acpr");
    model.nodeGroup("grp4").label("20 \u5c42\u6597\u7bf7\u6a21\u578b");

    model.component("comp1").physics("acpr").create("cwr4", "CylindricalWaveRadiation", 1);

    model.nodeGroup("grp4").add("cwr4");

    model.component("comp1").physics("acpr").feature("cwr4").selection().set(208);
    model.component("comp1").physics("acpr").feature("cwr4").set("r0", new String[]{"x4", "y4", "0"});
    model.component("comp1").physics("acpr").create("bpf4", "BackgroundPressureField", 2);

    model.nodeGroup("grp4").add("bpf4");

    model.component("comp1").physics("acpr").feature("bpf4").selection().named("geom1_boxsel10");
    model.component("comp1").physics("acpr").feature("bpf4").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf4").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf4").set("phi", "acpr.bpf4.k*x4");
    model.component("comp1").physics("acpr").create("sym4", "Symmetry", 1);

    model.nodeGroup("grp4").add("sym4");

    model.component("comp1").physics("acpr").feature("sym4").selection().named("geom1_boxsel6");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/6");
    model.component("comp1").mesh("mesh1").run("size");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(55);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(156);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 40);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg1").run();
    model.result("pg1").label("\u603b\u58f0\u538b (acpr)");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "0");
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").create("sel1", "Selection");
    model.result("pg1").feature("line1").feature("sel1").selection()
         .set(1, 2, 105, 106, 107, 108, 151, 152, 153, 154, 155, 156, 208, 209, 210, 211);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "x1", 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "y1+4.3[m]", 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u5747\u5300\u5316\u6597\u7bf7", 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "x2", 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "y2+4.3[m]", 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u65e0\u9690\u8eab", 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "x3", 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "y3+4.3[m]", 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "50 \u5c42\u6597\u7bf7", 2, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "x4", 3, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "y4+4.3[m]", 3, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "20 \u5c42\u6597\u7bf7", 3, 2);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").feature("tlan1").set("anchorpoint", "center");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u603b\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "acpr.Lp_t");
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u6563\u5c04\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "acpr.Lp_s");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().set(1, 2, 54, 55);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u4e3b\u65b9\u5411\u7b49\u6548\u58f0\u901f");
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().set(4);
    model.result("pg4").set("view", "new");
    model.result("pg4").set("applyselectiontodatasetedges", true);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("pris1", "PrincipalSurface");
    model.result("pg4").feature("pris1").setIndex("princvalstressexpr", 0, 0, 1);
    model.result("pg4").feature("pris1").setIndex("princvalstressexpr", 0.5, 0, 2);
    model.result("pg4").feature("pris1").setIndex("princdirstressexpr", "acpr.c_eff1x+acpr.c_eff2x", 0, 0);
    model.result("pg4").feature("pris1").setIndex("princdirstressexpr", "acpr.c_eff1y+acpr.c_eff2y", 1, 0);
    model.result("pg4").feature("pris1").setIndex("princdirstressexpr", 0, 1, 1);
    model.result("pg4").feature("pris1").setIndex("princdirstressexpr", "acpr.c_eff3x", 0, 2);
    model.result("pg4").feature("pris1").setIndex("princdirstressexpr", "acpr.c_eff3y", 1, 2);
    model.result("pg4").feature("pris1").setIndex("princdirstressexpr", 0, 2, 2);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6cbf\u6597\u7bf7\u8fb9\u754c\u7684\u603b\u58f0\u538b");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u58f0\u538b (Pa)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(156);
    model.result("pg5").feature("lngr1").set("expr", "acpr.p_b");
    model.result("pg5").feature("lngr1").set("titletype", "none");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "\u80cc\u666f\u58f0\u538b\u573a", 0);
    model.result("pg5").feature("lngr1").set("linemarker", "circle");
    model.result("pg5").feature("lngr1").set("markerpos", "interp");
    model.result("pg5").feature("lngr1").set("markers", 100);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "acpr.p_t");
    model.result("pg5").feature("lngr2").set("linemarker", "none");
    model.result("pg5").feature("lngr2").setIndex("legends", "\u5747\u5300\u5316\u6597\u7bf7\u6a21\u578b", 0);
    model.result("pg5").feature().duplicate("lngr3", "lngr2");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").selection().set(155);
    model.result("pg5").feature("lngr3").setIndex("legends", "50 \u5c42\u6597\u7bf7\u6a21\u578b", 0);
    model.result("pg5").feature().duplicate("lngr4", "lngr3");
    model.result("pg5").run();
    model.result("pg5").feature("lngr4").selection().set(210);
    model.result("pg5").feature("lngr4").setIndex("legends", "20 \u5c42\u6597\u7bf7\u6a21\u578b", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5747\u8d28\u6750\u6599\u4e2d\u7684\u58f0\u901f");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u58f0\u901f (m/s)");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(55);
    model.result("pg6").feature("lngr1").set("expr", "acpr.c_eff1");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("autosolution", false);
    model.result("pg6").feature("lngr1").set("autodescr", true);
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "acpr.c_eff3");
    model.result("pg6").set("ylog", true);
    model.result("pg6").run();
    model.result("pg1").set("applyselectiontodatasetedges", false);
    model.result("pg1").run();

    model.title("\u58f0\u9690\u8eab");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u80cc\u666f\u573a\u6765\u6c42\u89e3\u58f0\u6563\u5c04\u95ee\u9898\u3002\u8be5 App \u662f\u4e00\u4e2a\u7531\u8d85\u6750\u6599\u5236\u6210\u7684\u58f0\u9690\u8eab\u6597\u7bf7\uff0c\u5176\u4e2d\u91c7\u7528\u4e24\u79cd\u4e0d\u540c\u7c7b\u578b\u7684\u8d85\u6750\u6599\uff0c\u4e00\u79cd\u4f7f\u7528\u5177\u6709\u53ef\u53d8\u5c5e\u6027\u7684\u5404\u5411\u5f02\u6027\u58f0\u5b66\u6750\u6599\uff0c\u53e6\u4e00\u79cd\u4f7f\u7528\u5177\u6709\u53ef\u53d8\u5c5e\u6027\u7684\u4e24\u5c42\u6750\u6599\u3002\u7531\u4e8e\u8be5 App \u5177\u6709\u7a7a\u95f4\u76f8\u5173\u7684\u6750\u6599\u5c5e\u6027\u548c\u591a\u5c42\u7ed3\u6784\uff0c\u56e0\u6b64\u8fd9\u4e24\u79cd\u8d85\u6750\u6599\u5bf9\u4e8e\u5165\u5c04\u5e73\u9762\u538b\u529b\u6ce2\u51e0\u4e4e\u662f\u900f\u660e\u7684\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("acoustic_cloaking.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
