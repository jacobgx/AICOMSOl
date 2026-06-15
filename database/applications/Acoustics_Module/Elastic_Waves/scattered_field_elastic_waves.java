/*
 * scattered_field_elastic_waves.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class scattered_field_elastic_waves {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Elastic_Waves");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);

    model.param().label("\u6750\u6599\u53c2\u6570");
    model.param().set("rho", "1[kg/m^3]");
    model.param().descr("rho", "\u80cc\u666f\u6750\u6599\u7684\u5bc6\u5ea6");
    model.param().set("muLame", "1[Pa]");
    model.param().descr("muLame", "\u80cc\u666f\u6750\u6599\u7684\u526a\u5207\u6a21\u91cf");
    model.param().set("lambLame", "2.3[Pa]");
    model.param().descr("lambLame", "\u80cc\u666f\u6750\u6599\u7684\u4e00\u9636 Lam\u00e9 \u53c2\u6570");
    model.param().set("rho_o", "2[kg/m^3]");
    model.param().descr("rho_o", "\u5f39\u6027\u969c\u788d\u7269\u7684\u5bc6\u5ea6");
    model.param().set("muLame_o", "1.1[Pa]");
    model.param().descr("muLame_o", "\u5f39\u6027\u969c\u788d\u7269\u7684\u526a\u5207\u6a21\u91cf");
    model.param().set("lambLame_o", "2.5[Pa]");
    model.param().descr("lambLame_o", "\u4e00\u9636 Lam\u00e9 \u53c2\u6570\u5f39\u6027\u969c\u788d\u7269");
    model.param().create("par2");
    model.param("par2").label("P \u6ce2\u53c2\u6570");
    model.param("par2").set("cP", "sqrt((lambLame+2*muLame)/rho)");
    model.param("par2").descr("cP", "P \u6ce2\u901f\u5ea6");
    model.param("par2").set("wlengthP", "1[m]");
    model.param("par2").descr("wlengthP", "\u6ce2\u957f");
    model.param("par2").set("kP", "2*pi[rad]/wlengthP");
    model.param("par2").descr("kP", "\u6ce2\u6570");
    model.param("par2").set("omega", "kP*cP");
    model.param("par2").descr("omega", "\u89d2\u9891\u7387");
    model.param().create("par3");
    model.param("par3").label("S \u6ce2\u53c2\u6570");
    model.param("par3").set("cS", "sqrt(muLame/rho)");
    model.param("par3").descr("cS", "S \u6ce2\u901f\u5ea6");
    model.param("par3").set("kS", "omega/cS");
    model.param("par3").descr("kS", "\u6ce2\u6570");
    model.param("par3").set("wlengthS", "2*pi/kS");
    model.param("par3").descr("wlengthS", "\u6ce2\u957f");
    model.param().create("par4");
    model.param("par4").label("\u51e0\u4f55\u53c2\u6570");
    model.param("par4").set("R", "5[m]");
    model.param("par4").descr("R", "\u8ba1\u7b97\u57df\u7684\u534a\u5f84");
    model.param("par4").set("r_o", "1[m]");
    model.param("par4").descr("r_o", "\u5185\u542b\u7269\u7684\u534a\u5f84");
    model.param("par4").set("r_layer", "1[m]");
    model.param("par4").descr("r_layer", "PML \u7684\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "r_layer", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "R-r_layer-r_o", 1);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", 3);
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"2*(R+r_o)", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("arr1(1)", 9);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("arr1(2)", 9);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("pml1", "PML");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 5, 8);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Cylindrical");
    model.component("comp1").coordSystem().create("pml2", "PML");
    model.component("comp1").coordSystem("pml2").selection().set(9, 10, 13, 16);
    model.component("comp1").coordSystem("pml2").set("ScalingType", "Cylindrical");
    model.component("comp1").coordSystem().create("pml3", "PML");
    model.component("comp1").coordSystem("pml3").selection().set(17, 18, 21, 24);
    model.component("comp1").coordSystem("pml3").set("ScalingType", "Cylindrical");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u5165\u5c04 P \u6ce2");
    model.component("comp1").variable("var1").set("uP", "exp(-1i*kP*x-1i*pi/2+1i*phase)[m]");
    model.component("comp1").variable("var1").descr("uP", "\u5165\u5c04\u6ce2\uff1au \u573a");
    model.component("comp1").variable("var1").set("vP", "0[m]");
    model.component("comp1").variable("var1").descr("vP", "\u5165\u5c04\u6ce2\uff1av \u573a");
    model.component("comp1").variable("var1").set("eps11P", "d(uP,x)");
    model.component("comp1").variable("var1")
         .descr("eps11P", "\u5165\u5c04\u6ce2\uff1a\u5e94\u53d8\u5f20\u91cf\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var1").set("eps22P", "d(vP,y)");
    model.component("comp1").variable("var1")
         .descr("eps22P", "\u5165\u5c04\u6ce2\uff1a\u5e94\u53d8\u5f20\u91cf\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1").set("eps12P", "0.5*(d(uP,y)+d(vP,x))");
    model.component("comp1").variable("var1")
         .descr("eps12P", "\u5165\u5c04\u6ce2\uff1a\u5e94\u53d8\u5f20\u91cf\uff0c12 \u5206\u91cf");
    model.component("comp1").variable("var1").set("s11P", "(lambLame+2*muLame)*eps11P+lambLame*eps22P");
    model.component("comp1").variable("var1")
         .descr("s11P", "\u5165\u5c04\u6ce2\uff1a\u5e94\u529b\u5f20\u91cf\uff0c11 \u5206\u91cf");
    model.component("comp1").variable("var1").set("s22P", "lambLame*eps11P+(lambLame+2*muLame)*eps22P");
    model.component("comp1").variable("var1")
         .descr("s22P", "\u5165\u5c04\u6ce2\uff1a\u5e94\u529b\u5f20\u91cf\uff0c22 \u5206\u91cf");
    model.component("comp1").variable("var1").set("s12P", "2*muLame*eps12P");
    model.component("comp1").variable("var1")
         .descr("s12P", "\u5165\u5c04\u6ce2\uff1a\u5e94\u529b\u5f20\u91cf\uff0c12 \u5206\u91cf");

    model.component("comp1").physics("solid").feature("lemm1").label("\u80cc\u666f\u6750\u6599");
    model.component("comp1").physics("solid").feature("lemm1").set("IsotropicOption", "CpCs");
    model.component("comp1").physics("solid").feature("lemm1").set("cp_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("cp", "cP");
    model.component("comp1").physics("solid").feature("lemm1").set("cs_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("cs", "cS");
    model.component("comp1").physics("solid").feature("lemm1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("rho", "rho");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").label("\u7a7a\u8154\u5185\u542b\u7269\uff0cP \u6ce2");
    model.component("comp1").physics("solid").feature("bndl1").selection().set(29, 30, 33, 34);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"-(s11P*solid.nx+s12P*solid.ny)", "-(s12P*solid.nx+s22P*solid.ny)", "0"});
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1")
         .label("\u65e0\u9650\u521a\u6027\u5185\u542b\u7269\uff0cP \u6ce2");
    model.component("comp1").physics("solid").feature("disp1").selection().set(41, 42, 45, 46);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-uP", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-vP", 1);
    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 2);
    model.component("comp1").physics("solid").feature("lemm2").label("\u5f39\u6027\u5185\u542b\u7269\uff0cP \u6ce2");
    model.component("comp1").physics("solid").feature("lemm2").selection().set(25);
    model.component("comp1").physics("solid").feature("lemm2").set("IsotropicOption", "Lame");
    model.component("comp1").physics("solid").feature("lemm2").set("lambLame_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm2").set("lambLame", "lambLame_o");
    model.component("comp1").physics("solid").feature("lemm2").set("muLame_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm2").set("muLame", "muLame_o");
    model.component("comp1").physics("solid").feature("lemm2").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm2").set("rho", "rho_o");
    model.component("comp1").physics("solid").feature("lemm2").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm2").feature("iss1")
         .set("Sil", new String[]{"-s11P", "-s12P", "0", "-s12P", "-s22P", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").feature("lemm2").feature("iss1")
         .set("eil", new String[]{"-eps11P", "-eps12P", "0", "-eps12P", "-eps22P", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 2);
    model.component("comp1").physics("solid").feature("bl1")
         .label("\u4f53\u8f7d\u8377\uff08\u5f39\u6027\u5185\u542b\u7269\uff09\uff0cP \u6ce2");
    model.component("comp1").physics("solid").feature("bl1").selection().set(25);
    model.component("comp1").physics("solid").feature("bl1")
         .set("forceReferenceVolume", new String[]{"(rho-rho_o)*(-omega^2)*uP", "(rho-rho_o)*(-omega^2)*vP", "0"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection()
         .set(1, 2, 5, 8, 9, 10, 13, 16, 17, 18, 21, 24);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "wlengthS/6");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "wlengthS/8");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("P \u6ce2");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq").set("plist", "omega/2/pi[rad]");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u6563\u5c04 u \u573a");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u4f4d\u79fb\u573a\uff0cX \u5206\u91cf");
    model.result("pg1").set("paramindicator", "");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "u");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection()
         .set(3, 4, 6, 7, 11, 12, 14, 15, 19, 20, 22, 23, 25);
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 5, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 12, 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 5, 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 24, 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 5, 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 2, 2);
    model.result("pg1").run();
    model.result("pg1").feature("tlan1").set("anchorpoint", "center");
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u6563\u5c04 v \u573a");
    model.result("pg2").set("title", "\u4f4d\u79fb\u573a\uff0cY \u5206\u91cf");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "v");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u6563\u5c04\u4f4d\u79fb\u573a\u5927\u5c0f");
    model.result("pg3").set("title", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "solid.disp");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u6563\u5c04 P \u6ce2");
    model.result("pg4").set("title", "\u6563\u5c04 P \u6ce2");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "d(u,x)+d(v,y)");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortable", "Wave");
    model.result("pg4").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u6563\u5c04 S \u6ce2");
    model.result("pg5").set("title", "\u6563\u5c04 S \u6ce2");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "solid.curlUZ");
    model.result("pg5").feature("surf1").set("descr", "\u4f4d\u79fb\u65cb\u5ea6\uff0cZ \u5206\u91cf");
    model.result("pg5").run();
    model.result("pg3").run();
    model.result().duplicate("pg6", "pg3");
    model.result("pg6").run();
    model.result("pg6").label("\u603b\u4f4d\u79fb\u573a\u5927\u5c0f");
    model.result("pg6").set("title", "\u603b\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "sqrt((real(u+uP))^2+(real(v+vP))^2)");
    model.result("pg6").run();
    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");
    model.result("pg7").run();
    model.result("pg7").label("\u603b P \u6ce2");
    model.result("pg7").set("title", "\u603b P \u6ce2");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "d(u+uP,x)+d(v+vP,y)");
    model.result("pg5").run();
    model.result().duplicate("pg8", "pg5");
    model.result("pg8").run();
    model.result("pg8").label("\u603b S \u6ce2");
    model.result("pg8").set("title", "\u603b S \u6ce2");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "d(v+vP,x)-d(u+uP,y)");
    model.result("pg8").run();

    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").label("\u5165\u5c04 S \u6ce2");
    model.component("comp1").variable("var2").rename("uP", "uS");
    model.component("comp1").variable("var2").set("uS", "0[m]");
    model.component("comp1").variable("var2").rename("vP", "vS");
    model.component("comp1").variable("var2").set("vS", "exp(-1i*kS*x-1i*pi/2+1i*phase)[m]");
    model.component("comp1").variable("var2").rename("eps11P", "eps11S");
    model.component("comp1").variable("var2").set("eps11S", "d(uS,x)");
    model.component("comp1").variable("var2").rename("eps22P", "eps22S");
    model.component("comp1").variable("var2").set("eps22S", "d(vS,y)");
    model.component("comp1").variable("var2").rename("eps12P", "eps12S");
    model.component("comp1").variable("var2").set("eps12S", "0.5*(d(uS,y)+d(vS,x))");
    model.component("comp1").variable("var2").rename("s11P", "s11S");
    model.component("comp1").variable("var2").set("s11S", "(lambLame+2*muLame)*eps11S+lambLame*eps22S");
    model.component("comp1").variable("var2").rename("s22P", "s22S");
    model.component("comp1").variable("var2").set("s22S", "lambLame*eps11S+(lambLame+2*muLame)*eps22S");
    model.component("comp1").variable("var2").rename("s12P", "s12S");
    model.component("comp1").variable("var2").set("s12S", "2*muLame*eps12S");

    model.nodeGroup().create("grp1", "Physics", "solid");
    model.nodeGroup("grp1").placeAfter("dcnt1");
    model.nodeGroup("grp1").add("bndl1");
    model.nodeGroup("grp1").add("disp1");
    model.nodeGroup("grp1").add("lemm2");
    model.nodeGroup("grp1").add("bl1");
    model.nodeGroup("grp1").label("P \u6ce2");
    model.nodeGroup().duplicate("grp2", "grp1");
    model.nodeGroup("grp2").label("S \u6ce2");

    model.component("comp1").physics("solid").feature("bndl2").label("\u7a7a\u8154\u5185\u542b\u7269\uff0cS \u6ce2");
    model.component("comp1").physics("solid").feature("bndl2")
         .set("forceReferenceArea", new String[]{"-(s11S*solid.nx+s12S*solid.ny)", "-(s12S*solid.nx+s22S*solid.ny)", "0"});
    model.component("comp1").physics("solid").feature("disp2")
         .label("\u65e0\u9650\u521a\u6027\u5185\u542b\u7269\uff0cS \u6ce2");
    model.component("comp1").physics("solid").feature("disp2").setIndex("U0", "-uS", 0);
    model.component("comp1").physics("solid").feature("disp2").setIndex("U0", "-vS", 1);
    model.component("comp1").physics("solid").feature("lemm3").label("\u5f39\u6027\u5185\u542b\u7269\uff0cS \u6ce2");
    model.component("comp1").physics("solid").feature("lemm3").feature("iss1")
         .label("\u521d\u59cb\u5e94\u529b\u548c\u5e94\u53d8");
    model.component("comp1").physics("solid").feature("lemm3").feature("iss1")
         .set("Sil", new String[]{"-s11S", "-s12S", "0", "-s12S", "-s22S", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").feature("lemm3").feature("iss1")
         .set("eil", new String[]{"-eps11S", "-eps12S", "0", "-eps12S", "-eps22S", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").feature("bl2")
         .label("\u4f53\u8f7d\u8377\uff08\u5f39\u6027\u5185\u542b\u7269\uff09\uff0cS \u6ce2");
    model.component("comp1").physics("solid").feature("bl2")
         .set("forceReferenceVolume", new String[]{"(rho-rho_o)*(-omega^2)*uS", "(rho-rho_o)*(-omega^2)*vS", "0"});

    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2"});
    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").label("S \u6ce2");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("plist", "omega/2/pi[rad]");
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayaxis", "y");
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").label("\u5165\u5c04 P \u6ce2");
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").label("\u5165\u5c04 S \u6ce2");
    model.result("pg1").feature("surf2").set("data", "dset2");
    model.result("pg1").run();
    model.result("pg1").feature("tlan1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 15.5, 3, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 3, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 12, 4, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 15.5, 4, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 4, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 24, 5, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 15.5, 5, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 5, 2);
    model.result("pg1").run();
    model.result("pg1").create("tlan2", "TableAnnotation");
    model.result("pg1").feature("tlan2").set("arraydim", "1");
    model.result("pg1").feature("tlan2").set("source", "localtable");
    model.result("pg1").feature("tlan2").setIndex("localtablematrix", -9, 0, 0);
    model.result("pg1").feature("tlan2").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg1").feature("tlan2").setIndex("localtablematrix", "P \u6ce2", 0, 2);
    model.result("pg1").feature("tlan2").setIndex("localtablematrix", -9, 1, 0);
    model.result("pg1").feature("tlan2").setIndex("localtablematrix", 10.5, 1, 1);
    model.result("pg1").feature("tlan2").setIndex("localtablematrix", "S \u6ce2", 1, 2);
    model.result("pg1").feature("tlan2").set("anchorpoint", "middleleft");
    model.result("pg1").feature("tlan2").set("showpoint", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", -1.5);
    model.result("pg1").feature("surf1").set("rangecolormax", 1.5);
    model.result("pg1").feature("surf2").set("arraydim", "1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").set("arrayaxis", "y");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").label("\u5165\u5c04 P \u6ce2");
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").feature("surf2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").label("\u5165\u5c04 S \u6ce2");
    model.result("pg2").feature("surf2").set("data", "dset2");
    model.result("pg2").feature("surf1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormin", -1.5);
    model.result("pg2").feature("surf1").set("rangecolormax", 1.5);
    model.result("pg2").feature("surf2").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").feature("tlan1").set("arraydim", "1");
    model.result("pg2").run();
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 15.5, 3, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 3, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 12, 4, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 15.5, 4, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 4, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 24, 5, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 15.5, 5, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 5, 2);
    model.result("pg2").run();
    model.result("pg2").create("tlan2", "TableAnnotation");
    model.result("pg2").feature("tlan2").set("arraydim", "1");
    model.result("pg2").feature("tlan2").set("source", "localtable");
    model.result("pg2").feature("tlan2").setIndex("localtablematrix", -9, 0, 0);
    model.result("pg2").feature("tlan2").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg2").feature("tlan2").setIndex("localtablematrix", "P \u6ce2", 0, 2);
    model.result("pg2").feature("tlan2").setIndex("localtablematrix", -9, 1, 0);
    model.result("pg2").feature("tlan2").setIndex("localtablematrix", 10.5, 1, 1);
    model.result("pg2").feature("tlan2").setIndex("localtablematrix", "S \u6ce2", 1, 2);
    model.result("pg2").feature("tlan2").set("anchorpoint", "middleleft");
    model.result("pg2").feature("tlan2").set("showpoint", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("view", "view2");
    model.result("pg3").run();
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").set("arrayaxis", "y");
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").label("\u5165\u5c04 P \u6ce2");
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").label("\u5165\u5c04 S \u6ce2");
    model.result("pg3").feature("surf2").set("data", "dset2");
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormin", 0);
    model.result("pg3").feature("surf1").set("rangecolormax", 2.5);
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature("tlan1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 15.5, 3, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 3, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 12, 4, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 15.5, 4, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 4, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 24, 5, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 15.5, 5, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 5, 2);
    model.result("pg3").run();
    model.result("pg3").create("tlan2", "TableAnnotation");
    model.result("pg3").feature("tlan2").set("arraydim", "1");
    model.result("pg3").feature("tlan2").set("source", "localtable");
    model.result("pg3").feature("tlan2").setIndex("localtablematrix", -9, 0, 0);
    model.result("pg3").feature("tlan2").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg3").feature("tlan2").setIndex("localtablematrix", "P \u6ce2", 0, 2);
    model.result("pg3").feature("tlan2").setIndex("localtablematrix", -9, 1, 0);
    model.result("pg3").feature("tlan2").setIndex("localtablematrix", 10.5, 1, 1);
    model.result("pg3").feature("tlan2").setIndex("localtablematrix", "S \u6ce2", 1, 2);
    model.result("pg3").feature("tlan2").set("anchorpoint", "middleleft");
    model.result("pg3").feature("tlan2").set("showpoint", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("view", "view2");
    model.result("pg4").run();
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").set("arrayaxis", "y");
    model.result("pg4").feature("surf1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").label("\u5165\u5c04 P \u6ce2");
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").label("\u5165\u5c04 S \u6ce2");
    model.result("pg4").feature("surf2").set("data", "dset2");
    model.result("pg4").feature("tlan1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 15.5, 3, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 3, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 12, 4, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 15.5, 4, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 4, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 24, 5, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 15.5, 5, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 5, 2);
    model.result("pg4").run();
    model.result("pg4").create("tlan2", "TableAnnotation");
    model.result("pg4").feature("tlan2").set("arraydim", "1");
    model.result("pg4").feature("tlan2").set("source", "localtable");
    model.result("pg4").feature("tlan2").setIndex("localtablematrix", -9, 0, 0);
    model.result("pg4").feature("tlan2").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg4").feature("tlan2").setIndex("localtablematrix", "P \u6ce2", 0, 2);
    model.result("pg4").feature("tlan2").setIndex("localtablematrix", -9, 1, 0);
    model.result("pg4").feature("tlan2").setIndex("localtablematrix", 10.5, 1, 1);
    model.result("pg4").feature("tlan2").setIndex("localtablematrix", "S \u6ce2", 1, 2);
    model.result("pg4").feature("tlan2").set("anchorpoint", "middleleft");
    model.result("pg4").feature("tlan2").set("showpoint", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("view", "view2");
    model.result("pg5").run();
    model.result("pg5").set("plotarrayenable", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").set("arrayaxis", "y");
    model.result("pg5").feature("surf1").set("arraydim", "1");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").label("\u5165\u5c04 P \u6ce2");
    model.result("pg5").feature().duplicate("surf2", "surf1");
    model.result("pg5").feature("surf2").set("arraydim", "1");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").label("\u5165\u5c04 S \u6ce2");
    model.result("pg5").feature("surf2").set("data", "dset2");
    model.result("pg5").run();
    model.result("pg5").feature("tlan1").set("arraydim", "1");
    model.result("pg5").run();
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 15.5, 3, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 3, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 12, 4, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 15.5, 4, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 4, 2);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 24, 5, 0);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", 15.5, 5, 1);
    model.result("pg5").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 5, 2);
    model.result("pg5").run();
    model.result("pg5").create("tlan2", "TableAnnotation");
    model.result("pg5").feature("tlan2").set("arraydim", "1");
    model.result("pg5").feature("tlan2").set("source", "localtable");
    model.result("pg5").feature("tlan2").setIndex("localtablematrix", -9, 0, 0);
    model.result("pg5").feature("tlan2").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg5").feature("tlan2").setIndex("localtablematrix", "P \u6ce2", 0, 2);
    model.result("pg5").feature("tlan2").setIndex("localtablematrix", -9, 1, 0);
    model.result("pg5").feature("tlan2").setIndex("localtablematrix", 10.5, 1, 1);
    model.result("pg5").feature("tlan2").setIndex("localtablematrix", "S \u6ce2", 1, 2);
    model.result("pg5").feature("tlan2").set("anchorpoint", "middleleft");
    model.result("pg5").feature("tlan2").set("showpoint", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("view", "view2");
    model.result("pg6").run();
    model.result("pg6").set("plotarrayenable", true);
    model.result("pg6").set("arrayaxis", "y");
    model.result("pg6").feature("surf1").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").label("\u5165\u5c04 P \u6ce2");
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").feature("surf2").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").label("\u5165\u5c04 S \u6ce2");
    model.result("pg6").feature("surf2").set("data", "dset2");
    model.result("pg6").feature("surf2").set("expr", "sqrt((real(u+uS))^2+(real(v+vS))^2)");
    model.result("pg6").feature("tlan1").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 15.5, 3, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 3, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 12, 4, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 15.5, 4, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 4, 2);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 24, 5, 0);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", 15.5, 5, 1);
    model.result("pg6").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 5, 2);
    model.result("pg6").run();
    model.result("pg6").create("tlan2", "TableAnnotation");
    model.result("pg6").feature("tlan2").set("arraydim", "1");
    model.result("pg6").feature("tlan2").set("source", "localtable");
    model.result("pg6").feature("tlan2").setIndex("localtablematrix", -9, 0, 0);
    model.result("pg6").feature("tlan2").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg6").feature("tlan2").setIndex("localtablematrix", "P \u6ce2", 0, 2);
    model.result("pg6").feature("tlan2").setIndex("localtablematrix", -9, 1, 0);
    model.result("pg6").feature("tlan2").setIndex("localtablematrix", 10.5, 1, 1);
    model.result("pg6").feature("tlan2").setIndex("localtablematrix", "S \u6ce2", 1, 2);
    model.result("pg6").feature("tlan2").set("anchorpoint", "middleleft");
    model.result("pg6").feature("tlan2").set("showpoint", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("view", "view2");
    model.result("pg7").run();
    model.result("pg7").set("plotarrayenable", true);
    model.result("pg7").set("arrayaxis", "y");
    model.result("pg7").feature("surf1").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").label("\u5165\u5c04 P \u6ce2");
    model.result("pg7").feature().duplicate("surf2", "surf1");
    model.result("pg7").feature("surf2").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").label("\u5165\u5c04 S \u6ce2");
    model.result("pg7").feature("surf2").set("data", "dset2");
    model.result("pg7").feature("surf2").set("expr", "d(u+uS,x)+d(v+vS,y)");
    model.result("pg7").run();
    model.result("pg7").feature("tlan1").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 15.5, 3, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 3, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 12, 4, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 15.5, 4, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 4, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 24, 5, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", 15.5, 5, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 5, 2);
    model.result("pg7").run();
    model.result("pg7").create("tlan2", "TableAnnotation");
    model.result("pg7").feature("tlan2").set("arraydim", "1");
    model.result("pg7").feature("tlan2").set("source", "localtable");
    model.result("pg7").feature("tlan2").setIndex("localtablematrix", -9, 0, 0);
    model.result("pg7").feature("tlan2").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg7").feature("tlan2").setIndex("localtablematrix", "P \u6ce2", 0, 2);
    model.result("pg7").feature("tlan2").setIndex("localtablematrix", -9, 1, 0);
    model.result("pg7").feature("tlan2").setIndex("localtablematrix", 10.5, 1, 1);
    model.result("pg7").feature("tlan2").setIndex("localtablematrix", "S \u6ce2", 1, 2);
    model.result("pg7").feature("tlan2").set("anchorpoint", "middleleft");
    model.result("pg7").feature("tlan2").set("showpoint", false);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("view", "view2");
    model.result("pg8").run();
    model.result("pg8").set("plotarrayenable", true);
    model.result("pg8").set("arrayaxis", "y");
    model.result("pg8").feature("surf1").set("arraydim", "1");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").label("\u5165\u5c04 P \u6ce2");
    model.result("pg8").feature().duplicate("surf2", "surf1");
    model.result("pg8").feature("surf2").set("arraydim", "1");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").label("\u5165\u5c04 S \u6ce2");
    model.result("pg8").feature("surf2").set("data", "dset2");
    model.result("pg8").feature("surf2").set("expr", "d(v+vS,x)-d(u+uS,y)");
    model.result("pg8").feature("tlan1").set("arraydim", "1");
    model.result("pg8").run();
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", 15.5, 3, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 3, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", 12, 4, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", 15.5, 4, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 4, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", 24, 5, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", 15.5, 5, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 5, 2);
    model.result("pg8").run();
    model.result("pg8").create("tlan2", "TableAnnotation");
    model.result("pg8").feature("tlan2").set("arraydim", "1");
    model.result("pg8").feature("tlan2").set("source", "localtable");
    model.result("pg8").feature("tlan2").setIndex("localtablematrix", -9, 0, 0);
    model.result("pg8").feature("tlan2").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg8").feature("tlan2").setIndex("localtablematrix", "P \u6ce2", 0, 2);
    model.result("pg8").feature("tlan2").setIndex("localtablematrix", -9, 1, 0);
    model.result("pg8").feature("tlan2").setIndex("localtablematrix", 10.5, 1, 1);
    model.result("pg8").feature("tlan2").setIndex("localtablematrix", "S \u6ce2", 1, 2);
    model.result("pg8").feature("tlan2").set("anchorpoint", "middleleft");
    model.result("pg8").feature("tlan2").set("showpoint", false);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("view", "view2");
    model.result("pg1").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup("grp3").add("plotgroup", "pg1");
    model.nodeGroup("grp3").add("plotgroup", "pg2");
    model.nodeGroup("grp3").add("plotgroup", "pg3");
    model.nodeGroup("grp3").add("plotgroup", "pg4");
    model.nodeGroup("grp3").add("plotgroup", "pg5");
    model.nodeGroup("grp3").add("plotgroup", "pg6");
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").label("\u5e73\u9762\u6ce2");

    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-((R-r_layer-r_o)/2+r_o)*cos(pi/4)", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "((R-r_layer-r_o)/2+r_o)*sin(pi/4)", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").feature().move("pt1", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("c1", "pt1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("solid").feature("pl1").selection().set(29);
    model.component("comp1").physics("solid").feature("pl1").set("forcePoint", new int[]{1, 0, 0});

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u70b9\u6e90\u5165\u5c04\u573a");
    model.study("std1").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2", "solid/pl1"});
    model.study("std2").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1", "solid/pl1"});
    model.study("std3").feature("freq").set("plist", "omega/2/pi[rad]");
    model.study("std3").feature("freq").set("useadvanceddisable", true);
    model.study("std3").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1", "solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2"});
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u70b9\u6e90\u80cc\u666f\u573a");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").set("edges", false);
    model.result("pg9").set("plotarrayenable", true);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("arraydim", "1");
    model.result("pg9").feature("surf1").label("\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg9").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg9").feature("surf1").create("sel1", "Selection");
    model.result("pg9").feature("surf1").feature("sel1").selection().set(19, 20, 22, 23, 25);
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("rangecoloractive", true);
    model.result("pg9").feature("surf1").set("rangecolormin", 0);
    model.result("pg9").feature("surf1").set("rangecolormax", 0.15);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf2", "surf1");
    model.result("pg9").feature("surf2").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("surf2").label("P \u6ce2");
    model.result("pg9").feature("surf2").set("expr", "d(u,x)+d(v,y)");
    model.result("pg9").feature("surf2").set("rangecoloractive", false);
    model.result("pg9").feature("surf2").set("colortable", "Wave");
    model.result("pg9").feature("surf2").set("rangecoloractive", true);
    model.result("pg9").feature("surf2").set("rangecolormin", -0.8);
    model.result("pg9").feature("surf2").set("rangecolormax", 0.8);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("surf3", "surf2");
    model.result("pg9").feature("surf3").set("arraydim", "1");
    model.result("pg9").run();
    model.result("pg9").feature("surf3").label("S \u6ce2");
    model.result("pg9").feature("surf3").set("expr", "d(v,x)-d(u,y)");
    model.result("pg9").run();
    model.result("pg9").feature("surf3").set("rangecolormin", -4);
    model.result("pg9").feature("surf3").set("rangecolormax", 4);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").create("tlan1", "TableAnnotation");
    model.result("pg9").feature("tlan1").set("arraydim", "1");
    model.result("pg9").feature("tlan1").set("source", "localtable");
    model.result("pg9").feature("tlan1").setIndex("localtablematrix", 24, 0, 0);
    model.result("pg9").feature("tlan1").setIndex("localtablematrix", 5, 0, 1);
    model.result("pg9").feature("tlan1").setIndex("localtablematrix", "\u4f4d\u79fb", 0, 2);
    model.result("pg9").feature("tlan1").setIndex("localtablematrix", 34.4, 1, 0);
    model.result("pg9").feature("tlan1").setIndex("localtablematrix", 5, 1, 1);
    model.result("pg9").feature("tlan1").setIndex("localtablematrix", "P \u6ce2", 1, 2);
    model.result("pg9").feature("tlan1").setIndex("localtablematrix", 44.6, 2, 0);
    model.result("pg9").feature("tlan1").setIndex("localtablematrix", 5, 2, 1);
    model.result("pg9").feature("tlan1").setIndex("localtablematrix", "S \u6ce2", 2, 2);
    model.result("pg9").feature("tlan1").set("showpoint", false);
    model.result("pg9").feature("tlan1").set("anchorpoint", "center");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u70b9\u6e90\u80cc\u666f\u573a");
    model.result("pg9").set("paramindicator", "");

    model.view().create("view3", 2);

    model.result("pg9").run();
    model.result("pg9").set("view", "view3");

    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").label("\u70b9\u6e90");
    model.component("comp1").variable("var3").rename("uS", "uPS");
    model.component("comp1").variable("var3").set("uPS", "withsol('sol3',u)*exp(1i*phase)");
    model.component("comp1").variable("var3").rename("vS", "vPS");
    model.component("comp1").variable("var3").set("vPS", "withsol('sol3',v)*exp(1i*phase)");
    model.component("comp1").variable("var3").rename("eps11S", "eps11PS");
    model.component("comp1").variable("var3").set("eps11PS", "withsol('sol3',solid.el11)*exp(1i*phase)");
    model.component("comp1").variable("var3").rename("eps22S", "eps22PS");
    model.component("comp1").variable("var3").set("eps22PS", "withsol('sol3',solid.el22)*exp(1i*phase)");
    model.component("comp1").variable("var3").rename("eps12S", "eps12PS");
    model.component("comp1").variable("var3").set("eps12PS", "withsol('sol3',solid.el12)*exp(1i*phase)");
    model.component("comp1").variable("var3").rename("s11S", "s11PS");
    model.component("comp1").variable("var3").set("s11PS", "withsol('sol3',solid.sl11)*exp(1i*phase)");
    model.component("comp1").variable("var3").rename("s22S", "s22PS");
    model.component("comp1").variable("var3").set("s22PS", "withsol('sol3',solid.sl22)*exp(1i*phase)");
    model.component("comp1").variable("var3").rename("s12S", "s12PS");
    model.component("comp1").variable("var3").set("s12PS", "withsol('sol3',solid.sl12)*exp(1i*phase)");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().set(17, 18, 19, 20, 21, 22, 23, 24, 25);
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"x+24", "y"});
    model.component("comp1").cpl().duplicate("genext2", "genext1");
    model.component("comp1").cpl("genext2").set("dstmap", new String[]{"x+12", "y"});

    model.nodeGroup().duplicate("grp4", "grp2");
    model.nodeGroup("grp4").label("\u70b9\u6e90");

    model.component("comp1").physics("solid").feature("bndl3")
         .label("\u7a7a\u8154\u5185\u542b\u7269\uff0c\u70b9\u6e90");
    model.component("comp1").physics("solid").feature("bndl3")
         .set("forceReferenceArea", new String[]{"-(genext1(s11PS)*solid.nx+genext1(s12PS)*solid.ny)", "-(genext1(s12PS)*solid.nx+genext1(s22PS)*solid.ny)", "0"});
    model.component("comp1").physics("solid").feature("disp3")
         .label("\u65e0\u9650\u521a\u6027\u5185\u542b\u7269\uff0c\u70b9\u6e90");
    model.component("comp1").physics("solid").feature("disp3").setIndex("U0", "-genext2(uPS)", 0);
    model.component("comp1").physics("solid").feature("disp3").setIndex("U0", "-genext2(vPS)", 1);
    model.component("comp1").physics("solid").feature("lemm4")
         .label("\u5f39\u6027\u5185\u542b\u7269\uff0c\u70b9\u6e90");
    model.component("comp1").physics("solid").feature("lemm4").feature("iss1")
         .set("Sil", new String[]{"-s11PS", "-s12PS", "0", "-s12PS", "-s22PS", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").feature("lemm4").feature("iss1")
         .set("eil", new String[]{"-eps11PS", "-eps12PS", "0", "-eps12PS", "-eps22PS", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").feature("bl3")
         .label("\u4f53\u8f7d\u8377\uff08\u5f39\u6027\u5185\u542b\u7269\uff09\uff0c\u70b9\u6e90");
    model.component("comp1").physics("solid").feature("bl3")
         .set("forceReferenceVolume", new String[]{"(rho-rho_o)*(-omega^2)*uPS", "(rho-rho_o)*(-omega^2)*vPS", "0"});

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std4").label("\u70b9\u6e90\u6563\u5c04\u573a");
    model.study("std4").setGenPlots(false);
    model.study("std1").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2", "solid/pl1", "solid/bndl3", "solid/disp3", "solid/lemm4", "solid/bl3"});
    model.study("std2").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1", "solid/pl1", "solid/bndl3", "solid/disp3", "solid/lemm4", "solid/bl3"});
    model.study("std3").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1", "solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2", "solid/bndl3", "solid/disp3", 
         "solid/lemm4", "solid/bl3"});
    model.study("std4").feature("freq").set("plist", "omega/2/pi[rad]");
    model.study("std4").feature("freq").set("useadvanceddisable", true);
    model.study("std4").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1", "solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2", "solid/pl1"});
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").label("\u70b9\u6e90\u6563\u5c04\u4f4d\u79fb\u573a\u5927\u5c0f");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg10").set("paramindicator", "");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").create("sel1", "Selection");
    model.result("pg10").feature("surf1").feature("sel1").selection()
         .set(3, 4, 6, 7, 11, 12, 14, 15, 19, 20, 22, 23, 25);
    model.result("pg10").run();
    model.result("pg10").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg10").run();
    model.result("pg10").set("edges", false);
    model.result("pg10").create("tlan1", "TableAnnotation");
    model.result("pg10").feature("tlan1").set("source", "localtable");
    model.result("pg10").feature("tlan1").setIndex("localtablematrix", 0, 0, 0);
    model.result("pg10").feature("tlan1").setIndex("localtablematrix", 5, 0, 1);
    model.result("pg10").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 0, 2);
    model.result("pg10").feature("tlan1").setIndex("localtablematrix", 12, 1, 0);
    model.result("pg10").feature("tlan1").setIndex("localtablematrix", 5, 1, 1);
    model.result("pg10").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 1, 2);
    model.result("pg10").feature("tlan1").setIndex("localtablematrix", 24, 2, 0);
    model.result("pg10").feature("tlan1").setIndex("localtablematrix", 5, 2, 1);
    model.result("pg10").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 2, 2);
    model.result("pg10").feature("tlan1").set("showpoint", false);
    model.result("pg10").feature("tlan1").set("anchorpoint", "center");

    model.view().create("view4", 2);

    model.result("pg10").run();
    model.result("pg10").set("view", "view4");
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u70b9\u6e90\u603b\u4f4d\u79fb\u573a\u5927\u5c0f");
    model.result("pg11").run();
    model.result("pg11").feature("surf1").label("\u6563\u5c04\u573a\u516c\u5f0f");
    model.result("pg11").feature("surf1")
         .set("expr", "if(x>20,sqrt((real(u+uPS))^2+(real(v+vPS))^2),if(x<5,sqrt((real(u+genext1(uPS)))^2+(real(v+genext1(vPS)))^2),sqrt((real(u+genext2(uPS)))^2+(real(v+genext2(vPS)))^2)))");
    model.result("pg11").feature("surf1").set("rangecoloractive", true);
    model.result("pg11").feature("surf1").set("rangecolormin", 0);
    model.result("pg11").feature("surf1").set("rangecolormax", 0.15);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").set("title", "\u603b\u4f4d\u79fb\u573a\u5927\u5c0f");

    model.component("comp1").physics("solid").feature("pl1").selection().set(3, 16, 29);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(41, 42, 45, 46);

    model.study().create("std5");
    model.study("std5").create("freq", "Frequency");
    model.study("std5").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std5").label("\u70b9\u6e90\u603b\u573a\uff08\u65e0\u6563\u5c04\u573a\u516c\u5f0f\uff09");
    model.study("std5").setGenPlots(false);
    model.study("std1").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2", "solid/pl1", "solid/bndl3", "solid/disp3", "solid/lemm4", "solid/bl3", "solid/fix1"});
    model.study("std2").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1", "solid/pl1", "solid/bndl3", "solid/disp3", "solid/lemm4", "solid/bl3", "solid/fix1"});
    model.study("std3").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1", "solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2", "solid/bndl3", "solid/disp3", 
         "solid/lemm4", "solid/bl3", "solid/fix1"});
    model.study("std4").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1", "solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2", "solid/pl1", "solid/fix1"});
    model.study("std5").feature("freq").set("plist", "omega/2/pi[rad]");
    model.study("std5").feature("freq").set("useadvanceddisable", true);
    model.study("std5").feature("freq")
         .set("disabledphysics", new String[]{"solid/bndl1", "solid/disp1", "solid/lemm2", "solid/bl1", "solid/bndl2", "solid/disp2", "solid/lemm3", "solid/bl2", "solid/bndl3", "solid/disp3", 
         "solid/lemm4/iss1", "solid/bl3"});
    model.study("std5").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result("pg11").run();
    model.result("pg11").set("plotarrayenable", true);
    model.result("pg11").set("arrayaxis", "y");
    model.result("pg11").feature("surf1").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature().duplicate("surf2", "surf1");
    model.result("pg11").feature("surf2").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature("surf2").set("data", "dset5");
    model.result("pg11").feature("surf2").set("expr", "solid.disp");
    model.result("pg11").run();
    model.result("pg11").feature("surf2").set("inheritplot", "surf1");
    model.result("pg11").feature("surf2").label("\u65e0\u6563\u5c04\u573a\u516c\u5f0f");
    model.result("pg11").feature("tlan1").set("arraydim", "1");
    model.result("pg11").run();
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 0, 3, 0);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 15.5, 3, 1);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", "\u7a7a\u8154", 3, 2);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 12, 4, 0);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 15.5, 4, 1);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", "\u521a\u6027", 4, 2);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 24, 5, 0);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", 15.5, 5, 1);
    model.result("pg11").feature("tlan1").setIndex("localtablematrix", "\u5f39\u6027", 5, 2);
    model.result("pg11").run();
    model.result("pg11").create("tlan2", "TableAnnotation");
    model.result("pg11").feature("tlan2").set("arraydim", "1");
    model.result("pg11").feature("tlan2").set("source", "localtable");
    model.result("pg11").feature("tlan2").setIndex("localtablematrix", -9, 0, 0);
    model.result("pg11").feature("tlan2").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg11").feature("tlan2").setIndex("localtablematrix", "SFF", 0, 2);
    model.result("pg11").feature("tlan2").setIndex("localtablematrix", -9, 1, 0);
    model.result("pg11").feature("tlan2").setIndex("localtablematrix", 10.5, 1, 1);
    model.result("pg11").feature("tlan2").setIndex("localtablematrix", "\u65e0 SFF", 1, 2);
    model.result("pg11").feature("tlan2").set("anchorpoint", "middleleft");
    model.result("pg11").feature("tlan2").set("showpoint", false);
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").set("view", "new");
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("\u70b9\u6e90\u603b P \u6ce2");
    model.result("pg12").feature("surf1").set("arraydim", "1");
    model.result("pg12").run();
    model.result("pg12").feature("surf1")
         .set("expr", "if(x>20,d(u+uPS,x)+d(v+vPS,y),if(x<5,d(u+genext1(uPS),x)+d(v+genext1(vPS),y),d(u+genext2(uPS),x)+d(v+genext2(vPS),y)))");
    model.result("pg12").run();
    model.result("pg12").feature("surf1").set("colortable", "Wave");
    model.result("pg12").feature("surf1").set("rangecolormin", -0.5);
    model.result("pg12").feature("surf1").set("rangecolormax", 0.5);
    model.result("pg12").run();
    model.result("pg12").feature("surf2").set("arraydim", "1");
    model.result("pg12").run();
    model.result("pg12").feature("surf2").set("expr", "d(u,x)+d(v,y)");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result().duplicate("pg13", "pg12");
    model.result("pg13").run();
    model.result("pg13").label("\u70b9\u6e90\u603b S \u6ce2");
    model.result("pg13").feature("surf1").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("surf1")
         .set("expr", "if(x>20,-d(u+uPS,y)+d(v+vPS,x),if(x<5,-d(u+genext1(uPS),y)+d(v+genext1(vPS),x),-d(u+genext2(uPS),y)+d(v+genext2(vPS),x)))");
    model.result("pg13").feature("surf1").set("rangecolormin", -1);
    model.result("pg13").feature("surf1").set("rangecolormax", 1);
    model.result("pg13").run();
    model.result("pg13").feature("surf2").set("arraydim", "1");
    model.result("pg13").run();
    model.result("pg13").feature("surf2").set("expr", "-d(u,y)+d(v,x)");
    model.result("pg13").run();
    model.result("pg12").feature("surf1").set("arraydim", "1");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg9").run();

    model.nodeGroup().create("grp5", "Results");
    model.nodeGroup("grp5").set("type", "plotgroup");
    model.nodeGroup().move("grp5", 3);
    model.nodeGroup("grp5").add("plotgroup", "pg9");
    model.nodeGroup("grp5").add("plotgroup", "pg10");
    model.nodeGroup("grp5").add("plotgroup", "pg11");
    model.nodeGroup("grp5").add("plotgroup", "pg12");
    model.nodeGroup("grp5").add("plotgroup", "pg13");
    model.nodeGroup("grp5").label("\u70b9\u6e90");

    model.result("pg6").run();

    model.title("\u5f39\u6027\u6ce2\u7684\u6563\u5c04\u573a\u516c\u5f0f");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5728\u5df2\u77e5\u4e09\u79cd\u4e0d\u540c\u7c7b\u578b\u6563\u5c04\u4f53\uff08\u5373\u65e0\u9650\u521a\u6027\u6563\u5c04\u4f53\u3001\u7a7a\u8154\u548c\u5f39\u6027\u5939\u6742\u7269\uff09\u7684\u5165\u5c04\u573a\u7684\u60c5\u51b5\u4e0b\uff0c\u5982\u4f55\u6c42\u89e3\u6563\u5c04\u573a\u3002\u5f53\u6563\u5c04\u4f53\u4f4d\u4e8e\u6e90\u7684\u8fdc\u573a\u5e76\u4f7f\u63a2\u6d4b\u6ce2\u7c7b\u4f3c\u4e8e\u5e73\u9762\u6ce2\u65f6\uff0c\u8fd9\u4e2a\u516c\u5f0f\u975e\u5e38\u6709\u7528\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u5982\u9700\u5305\u542b\u6e90\uff0c\u5219\u9700\u8981\u5bf9\u4e00\u4e2a\u4e0d\u5fc5\u8981\u7684\u5de8\u5927\u8ba1\u7b97\u57df\u8fdb\u884c\u7f51\u683c\u5212\u5206\u3002\u56e0\u6b64\uff0c\u6211\u4eec\u5728\u6a21\u578b\u4e2d\u91c7\u7528 P \u5e73\u9762\u6ce2\u548c S \u5e73\u9762\u6ce2\u4f5c\u4e3a\u5165\u5c04\u573a\u3002\u6b64\u5916\uff0c\u8be5\u6a21\u578b\u8fd8\u6f14\u793a\u5982\u4f55\u5bf9\u70b9\u6e90\u53d1\u5c04\u7684\u573a\u8fdb\u884c\u6570\u503c\u8ba1\u7b97\uff0c\u7136\u540e\u4f7f\u7528\u8fd9\u4e2a\u89e3\u4f5c\u4e3a\u5df2\u77e5\u5165\u5c04\u573a\u8fdb\u884c\u540e\u7eed\u7814\u7a76\uff0c\u4ece\u800c\u6c42\u89e3\u6563\u5c04\u95ee\u9898\u3002");

    return model;
  }

  public static Model run3(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("scattered_field_elastic_waves.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
