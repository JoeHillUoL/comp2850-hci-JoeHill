
**Stop and check**:
- ✅ Instrumentation plan saved
- ✅ Events identified (at least 3)
- ✅ CSV schema defined
- ✅ Ethics considerations documented

---

## Reflection Questions

1. **Job stories vs. requirements**: Look at your backlog. How would the items differ if you'd written traditional requirements ("The system shall...") instead of job stories?

2. **Consent trade-offs**: Our protocol uses pseudonyms and no recordings. What insights might we *miss* by not recording audio? When would recordings be justified?

3. **Inclusion risk tagging**: Review your backlog. Did you find issues that affect *only* screen reader users, or do most issues affect multiple groups? What does this tell you about inclusive design?

4. **Evidence chains**: Pick one backlog item. Can you trace it from interview notes → job story → backlog → (future) fix → verification? What's missing in your chain?

---

## Further Reading

**Jobs-to-Be-Done**
- Christensen, C. M., Hall, T., Dillon, K., & Duncan, D. S. (2016). "Know Your Customers' Jobs to Be Done." *Harvard Business Review*. <https://hbr.org/2016/09/know-your-customers-jobs-to-be-done>
- Christensen, C. M., Hall, T., Dillon, K., & Duncan, D. S. (2016). *Competing Against Luck*. HarperCollins.

**Qualitative research methods**
- Lazar, J., Feng, J. H., & Hochheiser, H. (2017). *Research Methods in Human-Computer Interaction* (2nd ed.). Morgan Kaufmann. (Ch. 9: Interviews & Focus Groups)
- Braun, V., & Clarke, V. (2006). "Using thematic analysis in psychology." *Qualitative Research in Psychology*, 3(2), 77-101.

**Ethics & GDPR**
- ICO (2024). *Guide to the UK GDPR*. <https://ico.org.uk/for-organisations/uk-gdpr-guidance-and-resources/>
- BCS (2022). *Code of Conduct*. <https://www.bcs.org/membership-and-registrations/become-a-member/bcs-code-of-conduct/>

**Inclusive design**
- Microsoft (2024). *Inclusive Design Toolkit*. <https://inclusive.microsoft.design/>
- Holmes, K. (2018). *Mismatch: How Inclusion Shapes Design*. MIT Press.

---

## Glossary Summary

| Term | Definition | Example/Context |
|------|------------|-----------------|
| **Needs-finding** | Research to understand people's motivations, not just feature requests | Interviews reveal "I want confirmation" (need) vs. "Add a popup" (solution) |
| **Job story** | Situation-specific story format: When/I want/So I can/Because | "When filtering, I want persistence so I don't lose context because re-filtering is tedious" |
| **Informed consent** | Explaining research purpose, data use, and participant rights before collecting data | Consent script explains what you'll note, where it's stored, how to opt out |
| **Pseudonym** | Fake name to protect identity | "Participant A" instead of "Alice Smith" |
| **PII (Personally Identifiable Information)** | Data that can identify an individual (name, email, student ID) | Names = PII; random session IDs ≠ PII |
| **UK GDPR** | Data protection law (Data Protection Act 2018) | Right to access, delete, withdraw consent |
| **Inclusive backlog** | Backlog with severity + inclusion risk tags | "High severity, affects SR + Keyboard users" |
| **Thematic coding** | Identifying patterns in qualitative data | Tag interview notes with `confirmation`, `keyboard_nav` themes |
| **Severity** | Impact on task completion (High/Medium/Low) | High = blocks completion; Low = cosmetic |
| **Inclusion risk** | Who's affected (SR, Keyboard, Cognitive, Motor, Low vision) | SR + Cognitive = multiple groups at risk |

---

## Lab Checklist

Before leaving lab, confirm:

- [ ] **Consent protocol written**: `wk06/research/consent-protocol.md` complete with opt-out process
- [ ] **Interviews completed**: At least 2 peer interviews (ideally 3-4)
- [ ] **Notes saved**: `wk06/research/notes.md` with themes tagged
- [ ] **Job stories written**: 5-6 stories in `wk06/research/stories.md` with evidence links
- [ ] **Backlog populated**: 8+ items in `backlog/backlog.csv` with severity + inclusion risk
- [ ] **Candidate fixes selected**: 1-2 items marked `candidate_fix=true`
- [ ] **Instrumentation plan drafted**: `wk06/instrumentation/plan.md` outlines metrics for Week 9
- [ ] **Code committed**: `git add wk06/`, `git commit -m "wk6-lab2: needs-finding + consent + backlog"`

---

## Next Steps

In **Week 7 Lab 1** you will:
1. Review ethics guidance from guest speaker
2. Implement accessible inline edit (dual-path HTMX + no-JS)
3. Add validation with `aria-describedby` and `role="alert"`
4. Test with screen reader (NVDA/VoiceOver)

**Preparation**:
- Ensure scaffold from Lab 1 still runs (`./gradlew run`)
- Review backlog items marked `candidate_fix=true`
- Install NVDA (Windows) or enable VoiceOver (macOS) for testing

---

**Lab authored by**: COMP2850 Teaching Team, University of Leeds
**Last updated**: 2025-01-14
**Licence**: Academic use only (not for redistribution)