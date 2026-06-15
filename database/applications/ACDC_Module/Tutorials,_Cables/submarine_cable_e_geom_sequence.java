/*
 * submarine_cable_e_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class submarine_cable_e_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Tutorials,_Cables");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.param().label("\u51e0\u4f55\u53c2\u6570 1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Dcon", "26.2[mm]", "\u4e3b\u5bfc\u4f53\u76f4\u5f84\uff08\u76f8\uff09");
    model.param().set("Tins", "24.0[mm]", "\u7edd\u7f18\u539a\u5ea6 (XLPE)");
    model.param().set("Dins", "77.6[mm]", "\u7edd\u7f18\u76f4\u5f84\uff08XLPE \u548c SCC\uff09");
    model.param().set("Tscc", "(Dins/2-Dcon/2-Tins)/2", "\u534a\u5bfc\u4f53\u5316\u5408\u7269\u539a\u5ea6");
    model.param().set("Tpbs", "2.9[mm]", "\u94c5\u5305\u539a\u5ea6");
    model.param().set("Tpe", "2.9[mm]", "\u805a\u4e59\u70ef\u62a4\u5957\u539a\u5ea6");
    model.param()
         .set("Dpha", "Dins+2*Tpbs+2*Tpe", "\u76f8\u4f4d\u76f4\u5f84\uff08\u5305\u542b\u62a4\u5957\u548c PE\uff09");
    model.param().set("Dpha3", "Dpha*(2/sqrt(3)+1)", "\u4e09\u76f8\u7ed3\u5408\u7684\u76f4\u5f84");
    model.param().set("Dfic", "2.5[mm]", "\u5149\u7ea4\u7ea4\u82af\u76f4\u5f84");
    model.param().set("Tfih", "0.5[mm]", "\u94a2\u87ba\u65cb\u7ebf\u539a\u5ea6\uff08\u5149\u7ea4\uff09");
    model.param().set("Dfib", "9.2[mm]", "\u5149\u7f06\u76f4\u5f84");
    model.param().set("Dcab", "219.0[mm]", "\u6d77\u5e95\u7535\u7f06\u5916\u5f84");
    model.param().set("Darm", "(Dcab+Dpha3)/2", "\u94e0\u88c5\u73af\u4e2d\u5fc3\u76f4\u5f84");
    model.param().set("Tarm", "5.6[mm]", "\u94e0\u88c5\u539a\u5ea6\uff08\u7ebf\u5f84\uff09");
    model.param().set("Narm", "110", "\u73af\u4e2d\u7684\u94e0\u88c5\u7ebf\u6570");
    model.param().set("mfil", "0.5[mm]", "\u586b\u6599\u4f59\u91cf");
    model.param().set("marm", "pi*Darm/Narm-Tarm", "\u94e0\u88c5\u4f59\u91cf");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").label("\u76f8 1");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Dcon/2");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"-(Dpha/2)/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("c1").setIndex("pos", "Dpha/2", 1);
    model.component("comp1").geom("geom1").feature("c1").set("rot", 120);
    model.component("comp1").geom("geom1").feature("c1").set("selresult", true);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").label("\u76f8 2");
    model.component("comp1").geom("geom1").feature("c2").set("r", "Dcon/2");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("c2").set("selresult", true);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").label("\u76f8 3");
    model.component("comp1").geom("geom1").feature("c3").set("r", "Dcon/2");
    model.component("comp1").geom("geom1").feature("c3").set("pos", new String[]{"-(Dpha/2)/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("c3").setIndex("pos", "-Dpha/2", 1);
    model.component("comp1").geom("geom1").feature("c3").set("rot", -120);
    model.component("comp1").geom("geom1").feature("c3").set("selresult", true);
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c4").set("r", "Dcon/2+Tscc");
    model.component("comp1").geom("geom1").feature("c4").set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("c4").setIndex("layer", "Tscc", 0);
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").create("c5", "Circle");
    model.component("comp1").geom("geom1").feature("c5").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c5").set("r", "Dins/2-Tscc");
    model.component("comp1").geom("geom1").feature("c5").set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("c5").setIndex("layer", "Tins", 0);
    model.component("comp1").geom("geom1").run("c5");
    model.component("comp1").geom("geom1").create("c6", "Circle");
    model.component("comp1").geom("geom1").feature("c6").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c6").set("r", "Dins/2");
    model.component("comp1").geom("geom1").feature("c6").set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("c6").setIndex("layer", "Tscc", 0);
    model.component("comp1").geom("geom1").run("c6");
    model.component("comp1").geom("geom1").create("c7", "Circle");
    model.component("comp1").geom("geom1").feature("c7").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c7").set("r", "Dins/2+Tpbs");
    model.component("comp1").geom("geom1").feature("c7").set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("c7").setIndex("layer", "Tpbs", 0);
    model.component("comp1").geom("geom1").run("c7");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u534a\u5bfc\u4f53\u5316\u5408\u7269");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("c4", "c6");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").label("\u4ea4\u8054\u805a\u4e59\u70ef (XLPE)");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("c5");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni2").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").label("\u5c4f\u853d\u5c42");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").set("c7");
    model.component("comp1").geom("geom1").feature("uni3").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni3").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni3");
    model.component("comp1").geom("geom1").create("c8", "Circle");
    model.component("comp1").geom("geom1").feature("c8").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c8").set("r", "Dpha/2+mfil");
    model.component("comp1").geom("geom1").feature("c8").set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("c8").setIndex("layer", "Tpe+mfil", 0);
    model.component("comp1").geom("geom1").run("c8");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("r", "Dpha3/2-mfil");
    model.component("comp1").geom("geom1").feature("ca1").set("angle1", -60);
    model.component("comp1").geom("geom1").feature("ca1").set("angle2", 60);
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input")
         .set("c8", "ca1", "uni1", "uni2", "uni3");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "0[deg], 120[deg], 240[deg]");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("c9", "Circle");
    model.component("comp1").geom("geom1").feature("c9").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c9").set("r", "Dcab/2");
    model.component("comp1").geom("geom1").feature("c9").setIndex("layer", "Dcab/2-(Dpha3/2-mfil)", 0);
    model.component("comp1").geom("geom1").run("c9");
    model.component("comp1").geom("geom1").create("c10", "Circle");
    model.component("comp1").geom("geom1").feature("c10").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c10").set("r", "Darm/2+Tarm/2+marm");
    model.component("comp1").geom("geom1").feature("c10").setIndex("layer", "Tarm+2*marm", 0);
    model.component("comp1").geom("geom1").run("c10");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("\u805a\u4e59\u70ef");
    model.component("comp1").geom("geom1").feature("dif1").selection("input")
         .set("c9", "rot1(1)", "rot1(2)", "rot1(3)");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c10");
    model.component("comp1").geom("geom1").feature("dif1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("c11", "Circle");
    model.component("comp1").geom("geom1").feature("c11").set("r", "Dpha*0.241335+mfil");
    model.component("comp1").geom("geom1").feature("c11").set("pos", new String[]{"Dpha*-0.836015", "0"});
    model.component("comp1").geom("geom1").run("c11");
    model.component("comp1").geom("geom1").create("c12", "Circle");
    model.component("comp1").geom("geom1").feature("c12").set("r", "Dpha*0.118595+mfil");
    model.component("comp1").geom("geom1").feature("c12").set("pos", new String[]{"Dpha*-0.890205", "0"});
    model.component("comp1").geom("geom1").feature("c12").setIndex("pos", "Dpha*-0.355815", 1);
    model.component("comp1").geom("geom1").run("c12");
    model.component("comp1").geom("geom1").feature().duplicate("c13", "c12");
    model.component("comp1").geom("geom1").feature("c13").setIndex("pos", "Dpha*0.355815", 1);
    model.component("comp1").geom("geom1").run("c13");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("c11", "c12", "c13");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", "0[deg], 120[deg], 240[deg]");
    model.component("comp1").geom("geom1").feature("rot2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("rot2").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("c14", "Circle");
    model.component("comp1").geom("geom1").feature("c14").set("r", "Dpha*(1/sqrt(3)-1/2)+mfil");
    model.component("comp1").geom("geom1").run("c14");
    model.component("comp1").geom("geom1").create("c15", "Circle");
    model.component("comp1").geom("geom1").feature("c15").set("r", "Dfic/2+Tfih");
    model.component("comp1").geom("geom1").feature("c15").set("pos", new String[]{"Dpha*-0.836015", "0"});
    model.component("comp1").geom("geom1").run("c15");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").named("dif1");
    model.component("comp1").geom("geom1").feature("copy1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("copy1").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").label("\u805a\u4e19\u70ef");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").named("rot2");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("c14", "rot2");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").named("copy1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("c15", "copy1");
    model.component("comp1").geom("geom1").feature("dif2").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("dif2").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("c16", "Circle");
    model.component("comp1").geom("geom1").feature("c16").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c16").set("r", "Dfib/2");
    model.component("comp1").geom("geom1").feature("c16").set("pos", new String[]{"Dpha*-0.836015", "0"});
    model.component("comp1").geom("geom1").run("c16");
    model.component("comp1").geom("geom1").create("c17", "Circle");
    model.component("comp1").geom("geom1").feature("c17").label("\u94a2\u87ba\u65cb\u7ebf\uff08\u5149\u7ea4\uff09");
    model.component("comp1").geom("geom1").feature("c17").set("type", "curve");
    model.component("comp1").geom("geom1").feature("c17").set("r", "Dfic/2+Tfih");
    model.component("comp1").geom("geom1").feature("c17").set("pos", new String[]{"Dpha*-0.836015", "0"});
    model.component("comp1").geom("geom1").feature("c17").setIndex("layer", "Tfih", 0);
    model.component("comp1").geom("geom1").feature("c17").set("selresult", true);
    model.component("comp1").geom("geom1").run("c17");
    model.component("comp1").geom("geom1").create("c18", "Circle");
    model.component("comp1").geom("geom1").feature("c18").label("\u5149\u7ea4\u82af");
    model.component("comp1").geom("geom1").feature("c18").set("r", "Dfic/2");
    model.component("comp1").geom("geom1").feature("c18").set("pos", new String[]{"Dpha*-0.836015", "0"});
    model.component("comp1").geom("geom1").feature("c18").set("selresult", true);
    model.component("comp1").geom("geom1").run("c18");
    model.component("comp1").geom("geom1").create("c19", "Circle");
    model.component("comp1").geom("geom1").feature("c19").label("\u7535\u7f06\u94e0\u88c5");
    model.component("comp1").geom("geom1").feature("c19").set("r", "Tarm/2");
    model.component("comp1").geom("geom1").feature("c19").set("pos", new String[]{"Darm/2", "0"});
    model.component("comp1").geom("geom1").feature("c19").set("selresult", true);
    model.component("comp1").geom("geom1").run("c19");
    model.component("comp1").geom("geom1").create("rot3", "Rotate");
    model.component("comp1").geom("geom1").feature("rot3").selection("input").named("c19");
    model.component("comp1").geom("geom1").feature("rot3").set("rot", "360[deg]*range(1/Narm,1/Narm,1)");
    model.component("comp1").geom("geom1").run("rot3");
    model.component("comp1").geom("geom1").feature().duplicate("c20", "c10");
    model.component("comp1").geom("geom1").feature("c20").set("selresult", true);
    model.component("comp1").geom("geom1").feature("c20").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("c20");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").named("c19");
    model.component("comp1").geom("geom1").feature("copy2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("copy2").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").label("\u6ca5\u9752\u5316\u5408\u7269");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").named("c20");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").named("copy2");
    model.component("comp1").geom("geom1").feature("dif3").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("dif3").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").create("c21", "Circle");
    model.component("comp1").geom("geom1").feature("c21").label("\u7535\u7f06\u57df");
    model.component("comp1").geom("geom1").feature("c21").set("r", "Dcab/2");
    model.component("comp1").geom("geom1").feature("c21").set("selresult", true);
    model.component("comp1").geom("geom1").run("c21");
    model.component("comp1").geom("geom1").create("c22", "Circle");
    model.component("comp1").geom("geom1").feature("c22").label("\u7535\u78c1\u57df");
    model.component("comp1").geom("geom1").feature("c22").set("r", "5*Dcab/2");
    model.component("comp1").geom("geom1").feature("c22").set("selresult", true);
    model.component("comp1").geom("geom1").run("c22");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u70ed\u57df");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"10[m]", "8[m]"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-5[m]", "-4[m]"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "5[m]", 0);
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().named("geom1_r1_dom");
    model.component("comp1").material("mat1").selection().named("geom1_c22_dom");
    model.component("comp1").material("mat1").selection().named("geom1_c21_dom");
    model.component("comp1").material("mat1").selection().named("geom1_dif3_dom");
    model.component("comp1").material("mat1").selection().named("geom1_c19_dom");
    model.component("comp1").material("mat1").selection().named("geom1_c18_dom");
    model.component("comp1").material("mat1").selection().named("geom1_c17_dom");
    model.component("comp1").material("mat1").selection().named("geom1_dif2_dom");
    model.component("comp1").material("mat1").selection().named("geom1_dif1_dom");
    model.component("comp1").material("mat1").selection().named("geom1_uni3_dom");
    model.component("comp1").material("mat1").selection().named("geom1_uni2_dom");
    model.component("comp1").material("mat1").selection().named("geom1_uni1_dom");
    model.component("comp1").material("mat1").selection().named("geom1_c3_dom");
    model.component("comp1").material("mat1").selection().named("geom1_c2_dom");
    model.component("comp1").material("mat1").selection().named("geom1_c1_dom");
    model.component("comp1").material().remove("mat1");

    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("submarine_cable_e_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
